package com.projeto.dentalhelper.services.exceptions;

public class AgendamentoJaCadastradoNoHorarioException extends ServiceApplicationException {

	private static final long serialVersionUID = 1L;

	public AgendamentoJaCadastradoNoHorarioException(String mensagem) {
		super(mensagem);
	}

	public AgendamentoJaCadastradoNoHorarioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
