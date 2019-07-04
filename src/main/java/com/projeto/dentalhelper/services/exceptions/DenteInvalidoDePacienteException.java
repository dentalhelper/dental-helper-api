package com.projeto.dentalhelper.services.exceptions;

public class DenteInvalidoDePacienteException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public DenteInvalidoDePacienteException(String mensagem) {
		super(mensagem);
	}

	public DenteInvalidoDePacienteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
