package com.projeto.dentalhelper.services.exceptions;

public class EmailInvalidoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public EmailInvalidoException(String mensagem) {
		super(mensagem);
	}

	public EmailInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
