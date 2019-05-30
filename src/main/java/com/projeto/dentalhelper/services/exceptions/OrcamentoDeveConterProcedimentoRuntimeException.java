package com.projeto.dentalhelper.services.exceptions;

public class OrcamentoDeveConterProcedimentoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public OrcamentoDeveConterProcedimentoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public OrcamentoDeveConterProcedimentoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
