package com.projeto.dentalhelper.services.exceptions;

public class RecursoCpfOuRgDuplicadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RecursoCpfOuRgDuplicadoException(String mensagem) {
		super(mensagem);
	}

	public RecursoCpfOuRgDuplicadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
