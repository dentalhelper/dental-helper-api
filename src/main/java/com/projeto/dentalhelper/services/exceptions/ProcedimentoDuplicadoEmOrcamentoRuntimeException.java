package com.projeto.dentalhelper.services.exceptions;

public class ProcedimentoDuplicadoEmOrcamentoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcedimentoDuplicadoEmOrcamentoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public ProcedimentoDuplicadoEmOrcamentoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
