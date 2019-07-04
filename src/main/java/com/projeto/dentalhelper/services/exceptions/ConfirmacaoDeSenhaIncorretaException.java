package com.projeto.dentalhelper.services.exceptions;

public class ConfirmacaoDeSenhaIncorretaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public ConfirmacaoDeSenhaIncorretaException(String mensagem) {
		super(mensagem);
	}

	public ConfirmacaoDeSenhaIncorretaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
