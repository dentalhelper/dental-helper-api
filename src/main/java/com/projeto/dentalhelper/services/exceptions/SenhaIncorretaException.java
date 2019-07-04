package com.projeto.dentalhelper.services.exceptions;

public class SenhaIncorretaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException(String mensagem) {
		super(mensagem);
	}

	public SenhaIncorretaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
