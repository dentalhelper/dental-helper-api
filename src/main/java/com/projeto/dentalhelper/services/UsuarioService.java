package com.projeto.dentalhelper.services;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Cidade;
import com.projeto.dentalhelper.domains.Endereco;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Telefone;
import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.domains.enums.Sexo;
import com.projeto.dentalhelper.domains.enums.TipoUsuario;
import com.projeto.dentalhelper.dtos.UsuarioNovoDTO;
import com.projeto.dentalhelper.repositories.CidadeRepository;
import com.projeto.dentalhelper.repositories.PacienteRepository;
import com.projeto.dentalhelper.repositories.UsuarioRepository;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;
import com.projeto.dentalhelper.repositories.filter.UsuarioFilter;
import com.projeto.dentalhelper.services.exceptions.CpfJaCadastradoException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoLoginDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RgJaCadastradoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class UsuarioService extends AbstractService<Usuario, UsuarioRepository>{

	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	@Override
	public Usuario salvar(Usuario objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		objeto.setAtivo(true);
		
		CpfJaExiste(objeto, null);
		RgJaExiste(objeto, null);
		loginJaExiste(objeto, null);
		
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataCadastro(calendar.getTime());
		
		
		
		return repository.save(objeto);
	}
	
	
	@Override
	public Usuario atualizar(Long codigo, Usuario objetoModificado) throws ServiceApplicationException{
		Usuario objetoAtualizado = buscarPorCodigo(codigo);
		
		CpfJaExiste(objetoModificado, codigo);
		RgJaExiste(objetoModificado, codigo);
		loginJaExiste(objetoModificado, codigo);
		
		
		objetoModificado.getEndereco().setCodigo(objetoAtualizado.getEndereco().getCodigo());
		
		if(objetoModificado.getTelefones().size() >=1 && objetoAtualizado.getTelefones().size() >=1) {
			objetoModificado.getTelefones().get(0).setCodigo(objetoAtualizado.getTelefones().get(0).getCodigo());
			
			if(objetoModificado.getTelefones().size() > 1 && objetoAtualizado.getTelefones().size() > 1) {
				objetoModificado.getTelefones().get(1).setCodigo(objetoAtualizado.getTelefones().get(1).getCodigo());
			}
		}
			
		objetoAtualizado.setEndereco(objetoModificado.getEndereco());
		objetoAtualizado.getTelefones().clear();
		objetoAtualizado.getTelefones().addAll(objetoModificado.getTelefones());
		objetoAtualizado.getTelefones().forEach(telefone -> telefone.setPessoa(objetoAtualizado));
		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "ativo", "tipo", "dataCadastro", "telefones");
		return repository.save(objetoAtualizado);
	}
	
//	public String senhaToBcrypt(String senha) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder.encode(senha);
//	}
	
	public Usuario fromDTO(UsuarioNovoDTO objetoDTO) {
		
		Usuario usuario = new Usuario(objetoDTO.getNome(), objetoDTO.getDataNascimento(), 
				objetoDTO.getcPF(), objetoDTO.getrG(), EstadoCivil.toEnum(objetoDTO.getEstadoCivil()), 
				Sexo.toEnum(objetoDTO.getSexo()), objetoDTO.getEmail(), objetoDTO.getLogin(), 
				objetoDTO.getSenha(), true , TipoUsuario.toEnum(objetoDTO.getTipo()), null);
		
		
		
		
		Cidade cidade = cidadeRepository.getOne(objetoDTO.getCodigoCidade());
		
		Endereco endereco = new Endereco(objetoDTO.getLogradouro(), objetoDTO.getNumero(), 
				objetoDTO.getBairro(), objetoDTO.getCEP(), objetoDTO.getComplemento(), cidade);
		
		usuario.setEndereco(endereco);
		
		Telefone telefonePrincipal = new Telefone(objetoDTO.getTelefonePrincipal(), usuario);
		
		usuario.getTelefones().add(telefonePrincipal);
		
		if(objetoDTO.getTelefone2() != null) {
			Telefone telefone2 = new Telefone(objetoDTO.getTelefone2(), usuario);
			
			usuario.getTelefones().add(telefone2);
		}

		return usuario;
	}
	
	private boolean CpfJaExiste(Usuario objeto, Long codigoDoObjetoAtualizado) throws RecursoCpfDuplicadoException, CpfJaCadastradoException {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setCpf(objeto.getcPF());
		PacienteFilter filterPaciente = new PacienteFilter();
		filterPaciente.setCpf(objeto.getcPF());
		
		
		List<Usuario> listaDeObjetos = repository.buscarPorCpf(filter);
		
		List<Paciente> listaDePacientes = pacienteRepository.buscarPorCpf(filterPaciente);
		
		if(!listaDeObjetos.isEmpty()){
			Usuario usuarioExistente = listaDeObjetos.get(0);
			if(codigoDoObjetoAtualizado != null) {
				if(usuarioExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoCpfDuplicadoException(Long.toString(usuarioExistente.getCodigo()));
			
		}else if(!listaDePacientes.isEmpty()) {
			throw new CpfJaCadastradoException("Um Paciente com o cpf: '"+objeto.getcPF()+"' já existe");
		}
		
		return false;
		
	}
	
	private boolean RgJaExiste(Usuario objeto, Long codigoDoObjetoAtualizado) throws RecursoRgDuplicadoException, RgJaCadastradoException {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setRg(objeto.getrG());
		PacienteFilter filterPaciente = new PacienteFilter();
		filterPaciente.setRg(objeto.getrG());
		
		
		List<Usuario> listaDeObjetos = repository.buscarPorRg(filter);
		
		List<Paciente> listaDePacientes = pacienteRepository.buscarPorRg(filterPaciente);
		
		if(!listaDeObjetos.isEmpty()){
			Usuario usuarioExistente = listaDeObjetos.get(0);
			if(codigoDoObjetoAtualizado != null) {
				if(usuarioExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoRgDuplicadoException(Long.toString(usuarioExistente.getCodigo()));
			
		}else if(!listaDePacientes.isEmpty()) {
			throw new RgJaCadastradoException("Um Paciente com o rg: '"+objeto.getrG()+"' já existe");
		}
		
		return false;
		
	}
	
	
	private boolean loginJaExiste(Usuario objeto, Long codigoDoObjetoAtualizado) throws RecursoLoginDuplicadoException{
		
		List<Usuario> listaDeObjetos = repository.buscarPorLogin(objeto.getLogin());
		
		
		if(!listaDeObjetos.isEmpty()){
			Usuario usuarioExistente = listaDeObjetos.get(0);
			if(codigoDoObjetoAtualizado != null) {
				if(usuarioExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoLoginDuplicadoException(Long.toString(usuarioExistente.getCodigo()));
		}
		return false;
	}
	
	
}
