package com.projeto.dentalhelper.services.exceptions;

public class RecursoCpfDuplicadoRuntimeException extends ServiceApplicationRuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String linkRecurso;
	public RecursoCpfDuplicadoRuntimeException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoCpfDuplicadoRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem);
		this.linkRecurso = linkRecurso;
	}

	public RecursoCpfDuplicadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public String getLinkRecurso() {
		return linkRecurso;
	}

}
