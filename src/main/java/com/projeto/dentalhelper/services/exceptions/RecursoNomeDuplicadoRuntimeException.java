package com.projeto.dentalhelper.services.exceptions;

public class RecursoNomeDuplicadoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;
	private String linkRecurso;
	public RecursoNomeDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoNomeDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem);
		this.linkRecurso = linkRecurso;
	}

	public RecursoNomeDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public String getLinkRecurso() {
		return linkRecurso;
	}

}
