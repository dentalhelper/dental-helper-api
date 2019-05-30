package com.projeto.dentalhelper.services.exceptions;

public class ProcedimentoNaoEstaEmOrcamentoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcedimentoNaoEstaEmOrcamentoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public ProcedimentoNaoEstaEmOrcamentoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
