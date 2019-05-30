package com.projeto.dentalhelper.services.exceptions;

public class DespesaNaoPodeSerApagadaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public DespesaNaoPodeSerApagadaException(String mensagem) {
		super(mensagem);
	}

	public DespesaNaoPodeSerApagadaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
