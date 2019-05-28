package com.projeto.dentalhelper.services.exceptions;

public class RgJaCadastradoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public RgJaCadastradoException(String mensagem) {
		super(mensagem);
	}

	public RgJaCadastradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
