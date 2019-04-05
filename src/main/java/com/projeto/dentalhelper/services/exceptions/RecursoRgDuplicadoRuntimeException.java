package com.projeto.dentalhelper.services.exceptions;

public class RecursoRgDuplicadoRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoRgDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public RecursoRgDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public RecursoRgDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
