package com.projeto.dentalhelper.services.exceptions;

public class RecursoRgDuplicadoRuntimeException extends ServiceApplicationRuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String linkRecurso;
	public RecursoRgDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoRgDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem);
		this.linkRecurso = linkRecurso;
	}

	public RecursoRgDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public String getLinkRecurso() {
		return linkRecurso;
	}

}
