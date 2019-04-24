package com.projeto.dentalhelper.services.exceptions;

public class HoraAgendamentoInvalidaRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public HoraAgendamentoInvalidaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public HoraAgendamentoInvalidaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
