package com.projeto.dentalhelper.resources.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.resources.AbstractResource;

@Component
public class HATEOAS<C extends AbstractResource<O, ?>, O extends ObjetoIdentificado> {

	public void buildBasicLinks(C resourceController, O objeto, Long codigo) {
		objeto.add(linkTo(methodOn(resourceController.getClass()).getByCodigo(codigo)).withSelfRel()
				.withType("GET/DELETE/PUT").withDeprecation("false").withHreflang("pt-br").withMedia("JSON")
				.withTitle("Permite operações de busca, exclusão e atualização."));
	}
}
