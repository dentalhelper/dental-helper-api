package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.repositories.CategoriaDespesaRepository;
import com.projeto.dentalhelper.services.exceptions.RecursoNomeDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class CategoriaDespesaService extends AbstractService<CategoriaDespesa, CategoriaDespesaRepository> {

	private static final int PRIMEIRO_ITEM = 0;

	@Override
	public CategoriaDespesa salvar(CategoriaDespesa objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		nomeDeCategoriaExiste(objeto, null);
		return repository.save(objeto);
	}

	private boolean nomeDeCategoriaExiste(CategoriaDespesa objeto, Long codigoDoObjetoAtualizado) throws RecursoNomeDuplicadoException {
		List<CategoriaDespesa> listaDeObjetos = buscarPeloNome(objeto.getNome());
		if (listaDeObjetos.isEmpty()) {
			return false;
		} else {
			CategoriaDespesa categoriaExistente = obterCategoriaExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(categoriaExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;			
				}
			}
			throw new RecursoNomeDuplicadoException(Long.toString(categoriaExistente.getCodigo()));
		}
	}

	public List<CategoriaDespesa> buscarPeloNome(String nome) {
		return repository.findByNomeEquals(nome);
	}

	private CategoriaDespesa obterCategoriaExistente(List<CategoriaDespesa> listaDeObjetos) {
		return listaDeObjetos.get(PRIMEIRO_ITEM);
	}
	
	public CategoriaDespesa atualizar(Long codigo, CategoriaDespesa objetoModificado) throws ServiceApplicationException{

		nomeDeCategoriaExiste(objetoModificado, codigo);

		CategoriaDespesa objetoAtualizado = buscarPorCodigo(codigo);
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}

}
