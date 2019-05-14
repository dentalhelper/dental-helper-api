package com.projeto.dentalhelper.services.exceptions;

public class PagamentoSuperaValorTotalDoOrcamentoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public PagamentoSuperaValorTotalDoOrcamentoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public PagamentoSuperaValorTotalDoOrcamentoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
