package com.projeto.dentalhelper.services.exceptions;

public class DataAgendamentoInvalidaRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public DataAgendamentoInvalidaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public DataAgendamentoInvalidaRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public DataAgendamentoInvalidaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
