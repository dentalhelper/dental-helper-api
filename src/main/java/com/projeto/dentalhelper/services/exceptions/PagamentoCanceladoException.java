package com.projeto.dentalhelper.services.exceptions;

public class PagamentoCanceladoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public PagamentoCanceladoException(String mensagem) {
		super(mensagem);
	}

	public PagamentoCanceladoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
