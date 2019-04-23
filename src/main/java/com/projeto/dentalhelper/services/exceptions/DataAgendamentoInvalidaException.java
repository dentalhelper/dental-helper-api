package com.projeto.dentalhelper.services.exceptions;

public class DataAgendamentoInvalidaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public DataAgendamentoInvalidaException(String mensagem) {
		super(mensagem);
	}

	public DataAgendamentoInvalidaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
