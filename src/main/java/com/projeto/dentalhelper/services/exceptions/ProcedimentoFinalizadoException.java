package com.projeto.dentalhelper.services.exceptions;

public class ProcedimentoFinalizadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public ProcedimentoFinalizadoException(String mensagem) {
		super(mensagem);
	}

	public ProcedimentoFinalizadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
