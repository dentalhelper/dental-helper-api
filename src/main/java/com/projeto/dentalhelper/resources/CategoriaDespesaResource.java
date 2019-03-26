package com.projeto.dentalhelper.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.resources.api.CategoriaDespesaApi;
import com.projeto.dentalhelper.services.CategoriaDespesaService;
import com.projeto.dentalhelper.services.exceptions.*;

@RestController
public class CategoriaDespesaResource implements CategoriaDespesaApi {

	@Autowired
	private CategoriaDespesaService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	public ResponseEntity<CategoriaDespesa> post(@Valid CategoriaDespesa objeto, HttpServletResponse response) {

		CategoriaDespesa objetoSalvo = null;

		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo);
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private CategoriaDespesa salvar(CategoriaDespesa objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	private void lancarExceptionComLocation(ServiceApplicationException e) {
		CategoriaDespesa categoriaExistente = service.buscarPorCodigo(Long.parseLong(e.getMessage()));
		adicionarLink(categoriaExistente, categoriaExistente.getCodigo());
		throw new RecursoNomeDuplicadoRuntimeException(
				"Já existe uma categoria com esse nome: " + categoriaExistente.getNome(),
				categoriaExistente.getId().getHref());
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
	}

	public List<CategoriaDespesa> getAll() {
		List<CategoriaDespesa> objetos = service.buscarTodos();
		return adicionarReferencia(objetos);
	}

	public ResponseEntity<CategoriaDespesa> getByCodigo(@PathVariable Long codigo) {
		CategoriaDespesa objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	private void adicionarLink(CategoriaDespesa objeto, Long codigo) {
		objeto.add(linkTo(methodOn(this.getClass()).getByCodigo(codigo)).withSelfRel());
	}

	public ResponseEntity<CategoriaDespesa> put(Long codigo, @Valid CategoriaDespesa objetoModificado) {
		CategoriaDespesa objetoEditado = null;
		try {
			objetoEditado = atualizar(codigo, objetoModificado);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
	}
	
	private CategoriaDespesa atualizar(Long codigo, CategoriaDespesa objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	private List<CategoriaDespesa> adicionarReferencia(List<CategoriaDespesa> objetos) {
		ArrayList<CategoriaDespesa> objetosComReferencia = new ArrayList<CategoriaDespesa>();
		for (CategoriaDespesa objeto : objetos) {
			Long codigo = objeto.getCodigo();
			adicionarLink(objeto, codigo);
			objetosComReferencia.add(objeto);
		}
		return objetosComReferencia;
	}

}
