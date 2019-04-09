package com.projeto.dentalhelper.services.exceptions;

public class RespostaInvalidaRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public RespostaInvalidaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public RespostaInvalidaRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public RespostaInvalidaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
