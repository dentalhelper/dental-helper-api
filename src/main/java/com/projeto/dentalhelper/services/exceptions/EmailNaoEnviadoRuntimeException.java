package com.projeto.dentalhelper.services.exceptions;

public class EmailNaoEnviadoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNaoEnviadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public EmailNaoEnviadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
