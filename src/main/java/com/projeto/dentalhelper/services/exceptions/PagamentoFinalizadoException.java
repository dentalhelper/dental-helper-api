package com.projeto.dentalhelper.services.exceptions;

public class PagamentoFinalizadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public PagamentoFinalizadoException(String mensagem) {
		super(mensagem);
	}

	public PagamentoFinalizadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
