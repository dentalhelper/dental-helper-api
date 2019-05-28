package com.projeto.dentalhelper.services.exceptions;

public class RecursoLoginDuplicadoRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoLoginDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public RecursoLoginDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public RecursoLoginDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
