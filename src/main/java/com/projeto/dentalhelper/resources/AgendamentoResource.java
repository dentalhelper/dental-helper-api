package com.projeto.dentalhelper.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.resources.api.AgendamentoApi;
import com.projeto.dentalhelper.services.AgendamentoService;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoRunTimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class AgendamentoResource implements AgendamentoApi{
	
	@Autowired
	private AgendamentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public ResponseEntity<Agendamento> post(@Valid AgendamentoNovoDTO objeto, HttpServletResponse response) {

		Agendamento objetoSalvo = null;

		try {
			objetoSalvo = service.fromDTO(objeto);
			objetoSalvo = salvar(objetoSalvo);
			
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoRunTimeException(e.getMessage());
		} catch (ServiceApplicationException e) {

		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo);
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private Agendamento salvar(Agendamento objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}


	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
	}

	public List<Agendamento> getAll() {
		List<Agendamento> objetos = service.buscarTodos();
		return adicionarReferencia(objetos);
	}

	public ResponseEntity<Agendamento> getByCodigo(@PathVariable Long codigo) {
		Agendamento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	private void adicionarLink(Agendamento objeto, Long codigo) {
		objeto.add(linkTo(methodOn(this.getClass()).getByCodigo(codigo)).withSelfRel());
	}

	public ResponseEntity<Agendamento> put(Long codigo, @Valid @RequestBody AgendamentoNovoDTO objetoModificado) {
		Agendamento objetoEditado = null;
		try {
			objetoEditado = service.fromDTO(objetoModificado);
			objetoEditado = atualizar(codigo, objetoEditado);
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoRunTimeException(e.getMessage());
		} catch (ServiceApplicationException e) {

		}
		return ResponseEntity.ok(objetoEditado);
	}
	
	private Agendamento atualizar(Long codigo, Agendamento objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	private List<Agendamento> adicionarReferencia(List<Agendamento> objetos) {
		ArrayList<Agendamento> objetosComReferencia = new ArrayList<Agendamento>();
		for (Agendamento objeto : objetos) {
			Long codigo = objeto.getCodigo();
			adicionarLink(objeto, codigo);
			objetosComReferencia.add(objeto);
		}
		return objetosComReferencia;
	}


}
