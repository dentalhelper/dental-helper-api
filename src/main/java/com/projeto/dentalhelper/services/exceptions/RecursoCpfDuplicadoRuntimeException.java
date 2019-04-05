package com.projeto.dentalhelper.services.exceptions;

public class RecursoCpfDuplicadoRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoCpfDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public RecursoCpfDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public RecursoCpfDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
