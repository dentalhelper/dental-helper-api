package com.projeto.dentalhelper.services.exceptions;

public class DadoInvalidoRunTimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public DadoInvalidoRunTimeException(String mensagem) {
		super(mensagem);
	}

	public DadoInvalidoRunTimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public DadoInvalidoRunTimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
