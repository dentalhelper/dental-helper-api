package com.projeto.dentalhelper.services.exceptions;

public class RecursoLoginDuplicadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RecursoLoginDuplicadoException(String mensagem) {
		super(mensagem);
	}

	public RecursoLoginDuplicadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
