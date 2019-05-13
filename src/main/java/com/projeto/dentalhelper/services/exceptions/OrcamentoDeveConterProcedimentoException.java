package com.projeto.dentalhelper.services.exceptions;

public class OrcamentoDeveConterProcedimentoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public OrcamentoDeveConterProcedimentoException(String mensagem) {
		super(mensagem);
	}

	public OrcamentoDeveConterProcedimentoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
