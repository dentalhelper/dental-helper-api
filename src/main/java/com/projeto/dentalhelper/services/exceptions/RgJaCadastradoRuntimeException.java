package com.projeto.dentalhelper.services.exceptions;

public class RgJaCadastradoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public RgJaCadastradoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public RgJaCadastradoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
