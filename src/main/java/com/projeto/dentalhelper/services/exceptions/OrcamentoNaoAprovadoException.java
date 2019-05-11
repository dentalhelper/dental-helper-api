package com.projeto.dentalhelper.services.exceptions;

public class OrcamentoNaoAprovadoException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public OrcamentoNaoAprovadoException(String mensagem) {
		super(mensagem);
	}

	public OrcamentoNaoAprovadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
