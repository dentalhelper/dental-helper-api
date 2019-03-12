package com.projeto.dentalhelper.services;

import java.util.List;

import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationRuntimeException;

public interface CRUDService<O extends ObjetoIdentificado> {

	O salvar(O objeto) throws ServiceApplicationException;

	List<O> buscarTodos() throws ServiceApplicationRuntimeException;

	O buscarPorCodigo(Long codigo) throws ServiceApplicationRuntimeException;

	O atualizar(Long codigo, O objetoModificado) throws ServiceApplicationRuntimeException;

	void deletar(Long codigo) throws ServiceApplicationRuntimeException;
}
