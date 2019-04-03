package com.projeto.dentalhelper.services.exceptions;

public class RecursoCpfOuRgDuplicadoRuntimeException extends ServiceApplicationRuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String linkRecurso;
	public RecursoCpfOuRgDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoCpfOuRgDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem);
		this.linkRecurso = linkRecurso;
	}

	public RecursoCpfOuRgDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public String getLinkRecurso() {
		return linkRecurso;
	}

}
