package com.projeto.dentalhelper.services.exceptions;

public class DespesaNaoPodeSerEditadaRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public DespesaNaoPodeSerEditadaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public DespesaNaoPodeSerEditadaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
