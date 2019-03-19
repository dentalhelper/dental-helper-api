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

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.resources.api.DespesaApi;
import com.projeto.dentalhelper.services.DespesaService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

public class DespesaResource implements DespesaApi{
	
	@Autowired
	private DespesaService service;

	@Autowired
	private ApplicationEventPublisher publisher;


	@Override
	public ResponseEntity<Despesa> post(@Valid Despesa objeto, HttpServletResponse response) {
		Despesa objetoSalvo = null;

		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
//			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo);
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);

	}
	private Despesa salvar(Despesa objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}
	
	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
	}
	
	private void adicionarLink(Despesa objeto, Long codigo) {
		objeto.add(linkTo(methodOn(this.getClass()).getByCodigo(codigo)).withSelfRel());
	}

	@Override
	public List<Despesa> getAll() {
		List<Despesa> objetos = service.buscarTodos();
		return adicionarReferencia(objetos);
	}

	@Override
	public ResponseEntity<Despesa> getByCodigo(Long codigo) {
		Despesa objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Despesa> put(Long codigo, @Valid Despesa objetoModificado) {
		Despesa objetoEditado = service.atualizar(codigo, objetoModificado);
		return ResponseEntity.ok(objetoEditado);
	}

	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	private List<Despesa> adicionarReferencia(List<Despesa> objetos) {
		ArrayList<Despesa> objetosComReferencia = new ArrayList<Despesa>();
		for (Despesa objeto : objetos) {
			Long codigo = objeto.getCodigo();
			adicionarLink(objeto, codigo);
			objetosComReferencia.add(objeto);
		}
		return objetosComReferencia;
	}
	
	private List<Despesa> filtrar(DespesaFilter filter){
		return service.filtrar(filter);
		
	}

}
