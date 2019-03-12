package com.projeto.dentalhelper.services;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.dentalhelper.domains.*;
import com.projeto.dentalhelper.services.exceptions.*;

public abstract class AbstractService<O extends ObjetoIdentificado, R extends JpaRepository<O, Long>>
		implements CRUDService<O> {

	@Autowired
	protected R repository;

	@Override
	public O salvar(O objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		return repository.save(objeto);
	}

	@Override
	public List<O> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public O buscarPorCodigo(Long codigo) {
		Optional<O> objeto = repository.findById(codigo);
		return objeto.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + codigo + ", Tipo: " + CategoriaDespesa.class.getName()));
	}

	@Override
	public O atualizar(Long codigo, O objetoModificado) {
		O objetoAtualizado = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}

	@Override
	public void deletar (Long codigo)   {
		buscarPorCodigo(codigo);
		try {
			repository.deleteById(codigo);

		} catch (DataIntegrityViolationException e) {
			lancarIntegridadeDeDadosException(e);
		}
	}

	private void lancarIntegridadeDeDadosException(DataIntegrityViolationException e) {
		throw new IntegridadeDeDadosException(
				"Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro.");
	}
}
