package com.projeto.dentalhelper.services.exceptions;

public class RecursoCpfDuplicadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RecursoCpfDuplicadoException(String mensagem) {
		super(mensagem);
	}

	public RecursoCpfDuplicadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
