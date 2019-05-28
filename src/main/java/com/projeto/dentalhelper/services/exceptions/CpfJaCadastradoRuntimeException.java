package com.projeto.dentalhelper.services.exceptions;

public class CpfJaCadastradoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfJaCadastradoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public CpfJaCadastradoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
