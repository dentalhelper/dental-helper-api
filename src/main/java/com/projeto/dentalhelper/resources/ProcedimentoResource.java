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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.resources.api.ProcedimentoApi;
import com.projeto.dentalhelper.services.ProcedimentoService;
import com.projeto.dentalhelper.services.exceptions.RecursoNomeDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class ProcedimentoResource implements ProcedimentoApi{
	
	@Autowired
	ProcedimentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	public ResponseEntity<Procedimento> post(@Valid Procedimento objeto, HttpServletResponse response) {

		Procedimento objetoSalvo = null;

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

	private Procedimento salvar(Procedimento objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	private void lancarExceptionComLocation(ServiceApplicationException e) {
		Procedimento procedimentoExistente = service.buscarPorCodigo(Long.parseLong(e.getMessage()));
		adicionarLink(procedimentoExistente, procedimentoExistente.getCodigo());
		throw new RecursoNomeDuplicadoRuntimeException(
				"Já existe um procedimento com esse nome: " + procedimentoExistente.getNome(),
				procedimentoExistente.getId().getHref());
	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
	}

	public List<Procedimento> getByNome(@RequestParam(required = false, defaultValue = "%") String nome) {
		List<Procedimento> objetos = service.pesquisar(nome);
		return adicionarReferencia(objetos);
	}

	public ResponseEntity<Procedimento> getByCodigo(@PathVariable Long codigo) {
		Procedimento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	private void adicionarLink(Procedimento objeto, Long codigo) {
		objeto.add(linkTo(methodOn(this.getClass()).getByCodigo(codigo)).withSelfRel());
	}

	public ResponseEntity<Procedimento> put(Long codigo, @Valid Procedimento objetoModificado) {
		Procedimento objetoEditado = service.atualizar(codigo, objetoModificado);
		return ResponseEntity.ok(objetoEditado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	private List<Procedimento> adicionarReferencia(List<Procedimento> objetos) {
		ArrayList<Procedimento> objetosComReferencia = new ArrayList<Procedimento>();
		for (Procedimento objeto : objetos) {
			Long codigo = objeto.getCodigo();
			adicionarLink(objeto, codigo);
			objetosComReferencia.add(objeto);
		}
		return objetosComReferencia;
	}

	

}
