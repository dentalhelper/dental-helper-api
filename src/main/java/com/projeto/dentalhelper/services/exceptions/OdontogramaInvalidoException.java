package com.projeto.dentalhelper.services.exceptions;

public class OdontogramaInvalidoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public OdontogramaInvalidoException(String mensagem) {
		super(mensagem);
	}

	public OdontogramaInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
