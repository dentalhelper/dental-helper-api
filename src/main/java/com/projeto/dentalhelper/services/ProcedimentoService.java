package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
		nomeDoProcedimentoExiste(objeto, null);
		if(objeto.getDescricao() == null || objeto.getDescricao() == "") {
			objeto.setDescricao(objeto.getNome());
		}
		return repository.save(objeto);
	}
	
	private boolean nomeDoProcedimentoExiste(Procedimento objeto, Long codigoDoObjetoAtualizado) throws RecursoNomeDuplicadoException {
		List<Procedimento> listaDeObjetos = buscarPeloNome(objeto.getNome());
		if (listaDeObjetos.isEmpty()) {
			return false;
		} else {
			Procedimento procedimentoExistente = obterProcedimentoExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(procedimentoExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;			
				}
			}
			throw new RecursoNomeDuplicadoException(Long.toString(procedimentoExistente.getCodigo()));
		}
	}
	
	
	@Override
	public Procedimento atualizar(Long codigo, Procedimento objetoModificado) throws ServiceApplicationException{

		nomeDoProcedimentoExiste(objetoModificado, codigo);

		Procedimento objetoAtualizado = buscarPorCodigo(codigo);
		if(objetoModificado.getDescricao() == null || objetoModificado.getDescricao() == "") {
			objetoModificado.setDescricao(objetoModificado.getNome());
		}
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
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
