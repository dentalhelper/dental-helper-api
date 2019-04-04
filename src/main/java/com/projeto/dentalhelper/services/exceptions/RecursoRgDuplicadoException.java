package com.projeto.dentalhelper.services.exceptions;

public class RecursoRgDuplicadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RecursoRgDuplicadoException(String mensagem) {
		super(mensagem);
	}

	public RecursoRgDuplicadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
