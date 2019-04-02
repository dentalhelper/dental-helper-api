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
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.resources.api.PacienteAPI;
import com.projeto.dentalhelper.services.PacienteService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class PacienteResource implements PacienteAPI{
	
	@Autowired
	private PacienteService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public ResponseEntity<Paciente> post(@Valid Paciente objeto, HttpServletResponse response) {
		Paciente objetoSalvo = null;
		
		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
			
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo);
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}
	
	private Paciente salvar(Paciente objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}
	
	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
	}
	
	private void adicionarLink(Paciente objeto, Long codigo) {
		objeto.add(linkTo(methodOn(this.getClass()).getByCodigo(codigo)).withSelfRel());
	}
	

	@Override
	public List<Paciente> getAll() {
		List<Paciente> objetos = service.buscarTodos();

		return adicionarReferencia(objetos);
	}

	@Override
	public ResponseEntity<Paciente> getByCodigo(Long codigo) {
		Paciente objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Paciente> put(Long codigo, @Valid Paciente objetoModificado) {
		Paciente objetoEditado = null;
		try {
			objetoEditado = atualizar(codigo, objetoModificado);
		} catch (ServiceApplicationException e) {}
		return ResponseEntity.ok(objetoEditado);
	}
	
	private Paciente atualizar(Long codigo, Paciente objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}


	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	
	private List<Paciente> adicionarReferencia(List<Paciente> objetos) {
		ArrayList<Paciente> objetosComReferencia = new ArrayList<Paciente>();
		for (Paciente objeto : objetos) {
			Long codigo = objeto.getCodigo();
			adicionarLink(objeto, codigo);
			objetosComReferencia.add(objeto);
		}
		return objetosComReferencia;
	}

}
