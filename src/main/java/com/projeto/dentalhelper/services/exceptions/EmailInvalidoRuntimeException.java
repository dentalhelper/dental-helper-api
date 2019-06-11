package com.projeto.dentalhelper.services.exceptions;

public class EmailInvalidoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailInvalidoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public EmailInvalidoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
