package com.projeto.dentalhelper.services.exceptions;

public class SenhaIncorretaRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaIncorretaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public SenhaIncorretaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
