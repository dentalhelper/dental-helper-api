package com.projeto.dentalhelper.services.exceptions;

public class EmailNaoEnviadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public EmailNaoEnviadoException(String mensagem) {
		super(mensagem);
	}

	public EmailNaoEnviadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
