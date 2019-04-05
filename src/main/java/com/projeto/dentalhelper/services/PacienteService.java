package com.projeto.dentalhelper.services;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.repositories.PacienteRepository;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class PacienteService extends AbstractService<Paciente, PacienteRepository>{
	
	
	private static final int PRIMEIRO_ITEM = 0;
	
	@Override
	public Paciente salvar(Paciente objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		CpfJaExiste(objeto, null);
		RgJaExiste(objeto, null);
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataCriacaoFicha(calendar.getTime());
		
		
		return repository.save(objeto);
	}
	
	
	@Override
	public Paciente atualizar(Long codigo, Paciente objetoModificado) throws ServiceApplicationException {
		Paciente objetoAtualizado = buscarPorCodigo(codigo);
		
		CpfJaExiste(objetoModificado, codigo);
		RgJaExiste(objetoModificado, codigo);
		
		objetoModificado.setDataCriacaoFicha(objetoAtualizado.getDataCriacaoFicha());
		
		objetoAtualizado.setEndereco(objetoModificado.getEndereco());
		objetoAtualizado.getTelefones().clear();
		objetoAtualizado.getTelefones().addAll(objetoModificado.getTelefones());
		objetoAtualizado.getTelefones().forEach(telefone -> telefone.setPessoa(objetoAtualizado));
		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "telefones");
		return repository.save(objetoAtualizado);
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
	
	
	

}
