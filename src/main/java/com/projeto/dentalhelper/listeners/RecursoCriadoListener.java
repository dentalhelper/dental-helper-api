package com.projeto.dentalhelper.listeners;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.dentalhelper.events.RecursoCriadoEvent;

/**
 * Classe {@link RecursoCriadoListener} representa o ouvinte do evento
 * {@link RecursoCriadoEvent}
 */
@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();

		adicionarHeaderLocation(response, id);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		// Montando a URI da requisição atual
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();

		// Criando o Header de retorno, que indica onde o recurso salvo poderá pode ser
		// acessado (REST)
		response.setHeader("Location", uri.toASCIIString());
	}

}
