package com.projeto.dentalhelper.services.exceptions;

public class PagamentoFinalizadoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public PagamentoFinalizadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public PagamentoFinalizadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
