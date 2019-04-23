package com.projeto.dentalhelper.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.resources.api.ProcedimentoApi;
import com.projeto.dentalhelper.services.ProcedimentoService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class ProcedimentoResource extends AbstractResource<Procedimento, ProcedimentoService>
		implements ProcedimentoApi {

	public ResponseEntity<Procedimento> post(@Valid Procedimento objeto, HttpServletResponse response) {

		Procedimento objetoSalvo = null;

		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/procedimentos");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private Procedimento salvar(Procedimento objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	public List<Procedimento> getByNome(@RequestParam(required = false, defaultValue = "%") String nome) {
		List<Procedimento> objetos = service.pesquisar(nome);
		return adicionarLinks(objetos);
	}

	public ResponseEntity<Procedimento> getByCodigo(@PathVariable Long codigo) {
		Procedimento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<Procedimento> put(Long codigo, @Valid Procedimento objetoModificado) {
		Procedimento objetoEditado = null;
		try {
			objetoEditado = atualizar(codigo, objetoModificado);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
	}

	private Procedimento atualizar(Long codigo, Procedimento objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

}
