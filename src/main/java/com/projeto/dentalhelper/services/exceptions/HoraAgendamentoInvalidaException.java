package com.projeto.dentalhelper.services.exceptions;

public class HoraAgendamentoInvalidaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public HoraAgendamentoInvalidaException(String mensagem) {
		super(mensagem);
	}

	public HoraAgendamentoInvalidaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
