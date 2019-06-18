package com.projeto.dentalhelper.services.exceptions;

public class DenteInvalidoDePacienteRuntimeException extends ServiceApplicationRuntimeException {

	private static final long serialVersionUID = 1L;

	public DenteInvalidoDePacienteRuntimeException(String mensagem) {
		super(mensagem);
	}

	public DenteInvalidoDePacienteRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
