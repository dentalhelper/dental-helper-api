package com.projeto.dentalhelper.services.exceptions;

public class ProcedimentoNaoEstaEmOrcamentoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public ProcedimentoNaoEstaEmOrcamentoException(String mensagem) {
		super(mensagem);
	}

	public ProcedimentoNaoEstaEmOrcamentoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
