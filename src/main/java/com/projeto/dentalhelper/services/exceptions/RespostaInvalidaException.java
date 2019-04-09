package com.projeto.dentalhelper.services.exceptions;

public class RespostaInvalidaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RespostaInvalidaException(String mensagem) {
		super(mensagem);
	}

	public RespostaInvalidaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
