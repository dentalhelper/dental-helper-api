package com.projeto.dentalhelper.events;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * Classe {@link RecursoCriadoEvent} utilitária para criar o Header Location a partir de eventos
 */
public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long id;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getId() {
		return id;
	}

}
