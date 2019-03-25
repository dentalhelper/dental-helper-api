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
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;
import com.projeto.dentalhelper.resources.api.MaterialApi;
import com.projeto.dentalhelper.services.MaterialService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class MaterialResource implements MaterialApi{

	@Autowired
	private MaterialService service;

	@Autowired
	private ApplicationEventPublisher publisher;


	@Override
	public ResponseEntity<Material> post(@Valid Material objeto, HttpServletResponse response) {
		Material objetoSalvo = null;
		

		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
			
		}

		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo);
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);

	}
	
	
	private Material salvar(Material objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}
	
	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
		publisher.publishEvent(new RecursoCriadoEvent(this, response, codigo));
	}
	
	private void adicionarLink(Material objeto, Long codigo) {
		objeto.add(linkTo(methodOn(this.getClass()).getByCodigo(codigo)).withSelfRel());
	}
	
	@Override
	public List<Material> getByFilter(MaterialFilter filter) {
		List<Material> objetos = service.filtrar(filter);

		return adicionarReferencia(objetos);
	}

	@Override
	public ResponseEntity<Material> getByCodigo(@PathVariable Long codigo) {
		Material objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Material> put(Long codigo, @Valid Material objetoModificado) {
		Material objetoEditado = service.atualizar(codigo, objetoModificado);
		return ResponseEntity.ok(objetoEditado);
	}

	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().build();
	}
	
	private List<Material> adicionarReferencia(List<Material> objetos) {
		ArrayList<Material> objetosComReferencia = new ArrayList<Material>();
		for (Material objeto : objetos) {
			Long codigo = objeto.getCodigo();
			adicionarLink(objeto, codigo);
			objetosComReferencia.add(objeto);
		}
		return objetosComReferencia;
	}

}
