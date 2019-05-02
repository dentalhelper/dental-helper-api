package com.projeto.dentalhelper.services.exceptions;

public class AgendamentoJaCadastradoNoHorarioRuntimeException extends RecursoDuplicadoRuntimeException {

	private static final long serialVersionUID = 1L;

	public AgendamentoJaCadastradoNoHorarioRuntimeException(String mensagem) {
		super(mensagem);
	}

	public AgendamentoJaCadastradoNoHorarioRuntimeException(String mensagem, String linkRecurso) {
		super(mensagem, linkRecurso);
	}

	public AgendamentoJaCadastradoNoHorarioRuntimeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
