package com.projeto.dentalhelper.services.exceptions;

public class EmailIncorretoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailIncorretoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public EmailIncorretoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
