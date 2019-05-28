package com.projeto.dentalhelper.services.exceptions;

public class CpfJaCadastradoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public CpfJaCadastradoException(String mensagem) {
		super(mensagem);
	}

	public CpfJaCadastradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
