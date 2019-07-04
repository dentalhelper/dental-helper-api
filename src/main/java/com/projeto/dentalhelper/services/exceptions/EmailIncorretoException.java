package com.projeto.dentalhelper.services.exceptions;

public class EmailIncorretoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public EmailIncorretoException(String mensagem) {
		super(mensagem);
	}

	public EmailIncorretoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
