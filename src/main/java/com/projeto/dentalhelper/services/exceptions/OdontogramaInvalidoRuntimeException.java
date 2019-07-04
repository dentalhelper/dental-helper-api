package com.projeto.dentalhelper.services.exceptions;

public class OdontogramaInvalidoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public OdontogramaInvalidoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public OdontogramaInvalidoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
