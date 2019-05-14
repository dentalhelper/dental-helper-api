package com.projeto.dentalhelper.services.exceptions;

public class PagamentoSuperaValorTotalDoOrcamentoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public PagamentoSuperaValorTotalDoOrcamentoException(String mensagem) {
		super(mensagem);
	}

	public PagamentoSuperaValorTotalDoOrcamentoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
