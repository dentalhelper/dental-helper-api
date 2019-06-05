package com.projeto.dentalhelper.services.exceptions;

public class ConfirmacaoDeSenhaIncorretaRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfirmacaoDeSenhaIncorretaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public ConfirmacaoDeSenhaIncorretaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
