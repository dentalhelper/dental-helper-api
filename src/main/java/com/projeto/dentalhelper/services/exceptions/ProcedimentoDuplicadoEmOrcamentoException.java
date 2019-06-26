package com.projeto.dentalhelper.services.exceptions;

public class ProcedimentoDuplicadoEmOrcamentoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public ProcedimentoDuplicadoEmOrcamentoException(String mensagem) {
		super(mensagem);
	}

	public ProcedimentoDuplicadoEmOrcamentoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
