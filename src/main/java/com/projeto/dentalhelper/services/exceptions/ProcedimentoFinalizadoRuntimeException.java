package com.projeto.dentalhelper.services.exceptions;

public class ProcedimentoFinalizadoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public ProcedimentoFinalizadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public ProcedimentoFinalizadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
