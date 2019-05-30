package com.projeto.dentalhelper.services.exceptions;

public class DespesaNaoPodeSerEditadaException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public DespesaNaoPodeSerEditadaException(String mensagem) {
		super(mensagem);
	}

	public DespesaNaoPodeSerEditadaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
