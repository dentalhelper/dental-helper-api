package com.projeto.dentalhelper.services;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.repositories.PacienteRepository;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfOuRgDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoNomeDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class PacienteService extends AbstractService<Paciente, PacienteRepository>{
	
	
	private static final int PRIMEIRO_ITEM = 0;
	
	@Override
	public Paciente salvar(Paciente objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		rgOuCpfJaExiste(objeto, null);
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataCriacaoFicha(calendar.getTime());
		
		
		return repository.save(objeto);
	}
	
	
	@Override
	public Paciente atualizar(Long codigo, Paciente objetoModificado) throws ServiceApplicationException {
		Paciente objetoAtualizado = buscarPorCodigo(codigo);
		
		rgOuCpfJaExiste(objetoModificado, codigo);
		
		objetoModificado.setDataCriacaoFicha(objetoAtualizado.getDataCriacaoFicha());
		
		objetoAtualizado.setEndereco(objetoModificado.getEndereco());
		objetoAtualizado.getTelefones().clear();
		objetoAtualizado.getTelefones().addAll(objetoModificado.getTelefones());
		objetoAtualizado.getTelefones().forEach(telefone -> telefone.setPessoa(objetoAtualizado));
		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "telefones");
		return repository.save(objetoAtualizado);
	}
	
	private boolean rgOuCpfJaExiste(Paciente objeto, Long codigoDoObjetoAtualizado) throws RecursoCpfOuRgDuplicadoException {
		PacienteFilter filter = new PacienteFilter();
		filter.setCpf(objeto.getCPF());
		filter.setRg(objeto.getRG());
		
		List<Paciente> listaDeObjetos = repository.buscarPorCpfouRg(filter);
		
		if(listaDeObjetos.isEmpty()) {
			return false;
		} else {
			Paciente pacienteExistente = obterPacienteExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(pacienteExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoCpfOuRgDuplicadoException(Long.toString(pacienteExistente.getCodigo()));
		}
		
	}
	
	private Paciente obterPacienteExistente(List<Paciente> listaDeObjetos) {
		return listaDeObjetos.get(PRIMEIRO_ITEM);
	}
	
	

}
