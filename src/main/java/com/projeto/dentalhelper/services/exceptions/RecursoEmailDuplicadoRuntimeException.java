package com.projeto.dentalhelper.services.exceptions;

public class RecursoEmailDuplicadoRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoEmailDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public RecursoEmailDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public RecursoEmailDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
