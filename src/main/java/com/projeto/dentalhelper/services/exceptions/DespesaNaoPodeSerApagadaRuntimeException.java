package com.projeto.dentalhelper.services.exceptions;

public class DespesaNaoPodeSerApagadaRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public DespesaNaoPodeSerApagadaRuntimeException(String mensagem) {
		super(mensagem);
	}

	public DespesaNaoPodeSerApagadaRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
