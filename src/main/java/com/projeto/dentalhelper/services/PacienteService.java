package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Anamnese;
import com.projeto.dentalhelper.domains.Cidade;
import com.projeto.dentalhelper.domains.Endereco;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Questao;
import com.projeto.dentalhelper.domains.QuestaoPreDefinida;
import com.projeto.dentalhelper.domains.Telefone;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.domains.enums.Sexo;
import com.projeto.dentalhelper.dtos.PacienteNovoDTO;
import com.projeto.dentalhelper.repositories.CidadeRepository;
import com.projeto.dentalhelper.repositories.PacienteRepository;
import com.projeto.dentalhelper.repositories.QuestaoPreDefinidaRepository;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class PacienteService extends AbstractService<Paciente, PacienteRepository>{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private QuestaoPreDefinidaRepository questoesRepository;
	
	private static final int PRIMEIRO_ITEM = 0;
	
	@Override
	public Paciente salvar(Paciente objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		CpfJaExiste(objeto, null);
		RgJaExiste(objeto, null);
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataCriacaoFicha(calendar.getTime());
		
		Anamnese anamnese = new Anamnese();
		
		List<QuestaoPreDefinida> questoesPreDefinidas = questoesRepository.findAll();
		List<Questao> questoes = new ArrayList<Questao>();
		for(QuestaoPreDefinida qP: questoesPreDefinidas) {
			Questao q = new Questao();
			q.setAnamnese(anamnese);
			q.setDescricao(qP.getQuestao());
			q.setInformAdicionais("");
			questoes.add(q);
		}
		
		anamnese.setQuestoes(questoes);
		objeto.setAnamnese(anamnese);
		
		
		return repository.save(objeto);
	}
	
	
	@Override
	public Paciente atualizar(Long codigo, Paciente objetoModificado) throws ServiceApplicationException {
		Paciente objetoAtualizado = buscarPorCodigo(codigo);

		CpfJaExiste(objetoModificado, codigo);
		RgJaExiste(objetoModificado, codigo);
		
		
		
		objetoModificado.getEndereco().setCodigo(objetoAtualizado.getEndereco().getCodigo());
		objetoModificado.getTelefones().get(0).setCodigo(objetoAtualizado.getTelefones().get(0).getCodigo());
		
		if(objetoModificado.getTelefones().size() > 1 && objetoAtualizado.getTelefones().size() > 1) {
			objetoModificado.getTelefones().get(1).setCodigo(objetoAtualizado.getTelefones().get(1).getCodigo());
		}
		
			
		
		objetoModificado.setDataCriacaoFicha(objetoAtualizado.getDataCriacaoFicha());
		objetoAtualizado.setEndereco(objetoModificado.getEndereco());
		objetoAtualizado.getTelefones().clear();
		objetoAtualizado.getTelefones().addAll(objetoModificado.getTelefones());
		objetoAtualizado.getTelefones().forEach(telefone -> telefone.setPessoa(objetoAtualizado));

		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "telefones", "sexo", "anamnese");
		return repository.save(objetoAtualizado);
	}
	
	public Paciente atualizarAnamnese(Long codigo, Anamnese anamnese) throws ServiceApplicationException {
		Paciente paciente = buscarPorCodigo(codigo);
		
		Calendar calendar = new GregorianCalendar();
		anamnese.setDataResp(calendar.getTime());
		
		List<Questao> questoes = new ArrayList<Questao>();
		
		if(paciente.getAnamnese()!=null) {
			questoes = paciente.getAnamnese().getQuestoes();
		}
		
		if(questoes.size() <= anamnese.getQuestoes().size()) {
			for(int i = 0; i<questoes.size(); i++) {
				anamnese.getQuestoes().get(i).setCodigo(questoes.get(i).getCodigo());
			}
		} else {
			if(anamnese.getQuestoes().size() > 0) {
				if(questoes.size() > 0) {
					for(int i = 0; i<anamnese.getQuestoes().size(); i++) {
						anamnese.getQuestoes().get(i).setCodigo(questoes.get(i).getCodigo());
					}
				}
			}
		}
		
		anamnese.getQuestoes().forEach(questao -> questao.setAnamnese(anamnese));
		
		paciente.setAnamnese(anamnese);
		
		
		return repository.save(paciente);
	}
	
	
	public List<Paciente> pesquisar(String filtro){
		
		String cpf = filtro.trim();
		char[] c = cpf.toCharArray();
		
		PacienteFilter filter = new PacienteFilter();
		
		if(Character.isDigit(c[0])) {
			filter.setCpf(cpf);
			return repository.buscarPorCpf(filter);
		}
		
		filter.setNome(filtro);
		return repository.filtrar(filter);
		
	}
	
	
	private boolean CpfJaExiste(Paciente objeto, Long codigoDoObjetoAtualizado) throws RecursoCpfDuplicadoException {
		PacienteFilter filter = new PacienteFilter();
		filter.setCpf(objeto.getcPF());
		
		List<Paciente> listaDeObjetos = repository.buscarPorCpf(filter);
		
		if(listaDeObjetos.isEmpty()) {
			return false;
		} else {
			Paciente pacienteExistente = obterPacienteExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(pacienteExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoCpfDuplicadoException(Long.toString(pacienteExistente.getCodigo()));
		}
		
	}
	
	private boolean RgJaExiste(Paciente objeto, Long codigoDoObjetoAtualizado) throws RecursoRgDuplicadoException {
		PacienteFilter filter = new PacienteFilter();
		filter.setRg(objeto.getrG());
		
		List<Paciente> listaDeObjetos = repository.buscarPorRg(filter);
		
		if(listaDeObjetos.isEmpty()) {
			return false;
		} else {
			Paciente pacienteExistente = obterPacienteExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(pacienteExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoRgDuplicadoException(Long.toString(pacienteExistente.getCodigo()));
		}
		
	}
	
	
	
	private Paciente obterPacienteExistente(List<Paciente> listaDeObjetos) {
		return listaDeObjetos.get(PRIMEIRO_ITEM);
	}
	
	
	
	public Paciente fromDTO(PacienteNovoDTO objetoDTO) {
		Paciente paciente = new Paciente(objetoDTO.getNome(), objetoDTO.getDataNascimento(), 
				objetoDTO.getcPF(), objetoDTO.getrG(), EstadoCivil.toEnum(objetoDTO.getEstadoCivil()), 
				Sexo.toEnum(objetoDTO.getSexo()), objetoDTO.getEmail(), objetoDTO.getProfissao(), 
				objetoDTO.getFotoPerfil());
		
		Cidade cidade = cidadeRepository.getOne(objetoDTO.getCodigoCidade());
		
		Endereco endereco = new Endereco(objetoDTO.getLogradouro(), objetoDTO.getNumero(), 
				objetoDTO.getBairro(), objetoDTO.getCEP(), objetoDTO.getComplemento(), cidade);
		
		paciente.setEndereco(endereco);
		
		Telefone telefonePrincipal = new Telefone(objetoDTO.getTelefonePrincipal(), paciente);
		
		paciente.getTelefones().add(telefonePrincipal);
		
		if(objetoDTO.getTelefone2() != null) {
			Telefone telefone2 = new Telefone(objetoDTO.getTelefone2(), paciente);
			
			paciente.getTelefones().add(telefone2);
		}

		return paciente;
	}
	

}
