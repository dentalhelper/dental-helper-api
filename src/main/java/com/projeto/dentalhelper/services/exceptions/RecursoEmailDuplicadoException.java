package com.projeto.dentalhelper.services.exceptions;

public class RecursoEmailDuplicadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RecursoEmailDuplicadoException(String mensagem) {
		super(mensagem);
	}

	public RecursoEmailDuplicadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
