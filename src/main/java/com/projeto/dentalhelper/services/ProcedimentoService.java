package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.repositories.ProcedimentoRepository;
import com.projeto.dentalhelper.services.exceptions.RecursoNomeDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class ProcedimentoService extends AbstractService<Procedimento, ProcedimentoRepository>{
	
	private static final int PRIMEIRO_ITEM = 0;
	
	@Override
	public Procedimento salvar(Procedimento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		nomeDoProcedimentoExiste(objeto);
		return repository.save(objeto);
	}
	
	private boolean nomeDoProcedimentoExiste(Procedimento objeto) throws RecursoNomeDuplicadoException {
		List<Procedimento> listaDeObjetos = buscarPeloNome(objeto.getNome());
		if (listaDeObjetos.isEmpty()) {
			return false;
		} else {
			Procedimento procedimentoExistente = obterProcedimentoExistente(listaDeObjetos);
			throw new RecursoNomeDuplicadoException(Long.toString(procedimentoExistente.getCodigo()));
		}
	}
	
	public List<Procedimento> buscarPeloNome(String nome) {
		return repository.findByNomeEquals(nome);
	}
	
	public List<Procedimento> pesquisar(String nome) {
		return repository.findByNomeContaining(nome);
	}
	
	private Procedimento obterProcedimentoExistente(List<Procedimento> listaDeObjetos) {
		return listaDeObjetos.get(PRIMEIRO_ITEM);
	}

}
