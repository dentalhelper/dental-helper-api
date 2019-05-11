package com.projeto.dentalhelper.services.exceptions;

public class OrcamentoNaoAprovadoRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public OrcamentoNaoAprovadoRuntimeException(String mensagem) {
		super(mensagem);
	}

	public OrcamentoNaoAprovadoRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
