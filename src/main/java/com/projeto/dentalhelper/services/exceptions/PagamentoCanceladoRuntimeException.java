package com.projeto.dentalhelper.services.exceptions;

public class PagamentoCanceladoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public PagamentoCanceladoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public PagamentoCanceladoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
