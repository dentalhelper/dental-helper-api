package com.projeto.dentalhelper.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Anamnese;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.dtos.PacienteNovoDTO;
import com.projeto.dentalhelper.dtos.PacienteResumoDTO;
import com.projeto.dentalhelper.events.RecursoCriadoEvent;
import com.projeto.dentalhelper.resources.api.PacienteAPI;
import com.projeto.dentalhelper.services.PacienteService;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class PacienteResource implements PacienteAPI {

	@Autowired
	private PacienteService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Override
	public ResponseEntity<Paciente> post(@Valid PacienteNovoDTO objetoDTO, HttpServletResponse response) {
		Paciente objetoSalvo = service.fromDTO(objetoDTO);

		try {
			objetoSalvo = salvar(objetoSalvo);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
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
	public ResponseEntity<Paciente> getByCodigo(@PathVariable Long codigo) {
		Paciente objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Paciente> put(Long codigo, @Valid PacienteNovoDTO objetoDTOModificado) {
		Paciente pacienteFromDTO = service.fromDTO(objetoDTOModificado);
		pacienteFromDTO.setCodigo(codigo);
		Paciente objetoEditado = null;
		try {
			objetoEditado = atualizar(codigo, pacienteFromDTO);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
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

	private void lancarExceptionComLocation(ServiceApplicationException e) {
		Paciente pacienteExistente = service.buscarPorCodigo(Long.parseLong(e.getMessage()));
		adicionarLink(pacienteExistente, pacienteExistente.getCodigo());
		if (e instanceof RecursoCpfDuplicadoException) {
			throw new RecursoCpfDuplicadoRuntimeException(
					"Já existe um paciente com esse cpf: " + pacienteExistente.getcPF(),
					pacienteExistente.getId().getHref());
		}

		throw new RecursoRgDuplicadoRuntimeException("Já existe um paciente com esse rg: " + pacienteExistente.getrG(),
				pacienteExistente.getId().getHref());
	}

	@Override
	public ResponseEntity<List<PacienteResumoDTO>> getByFilter(
			@RequestParam(required = false, defaultValue = "%") String filtro) {
		List<Paciente> objetos = service.pesquisar(filtro);
		adicionarReferencia(objetos);
		List<PacienteResumoDTO> pacienteResumoDTO = objetos.stream().map(paciente -> new PacienteResumoDTO(paciente))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(pacienteResumoDTO);

	}

	@Override
	public ResponseEntity<Paciente> put(@PathVariable Long codigo,@Valid @RequestBody Anamnese anamnese) {
		Paciente objetoEditado = null;
		try {
			objetoEditado = service.atualizarAnamnese(codigo, anamnese);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
		
	}

}
