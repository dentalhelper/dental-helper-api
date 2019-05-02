package com.projeto.dentalhelper.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.resources.api.CategoriaDespesaApi;
import com.projeto.dentalhelper.services.CategoriaDespesaService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class CategoriaDespesaResource extends AbstractResource<CategoriaDespesa, CategoriaDespesaService>
		implements CategoriaDespesaApi {

	public ResponseEntity<CategoriaDespesa> post(@Valid CategoriaDespesa objeto, HttpServletResponse response) {

		CategoriaDespesa objetoSalvo = null;

		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/categorias-despesa");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private CategoriaDespesa salvar(CategoriaDespesa objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	public List<CategoriaDespesa> getAll() {
		List<CategoriaDespesa> objetos = service.buscarTodos();
		return adicionarLinks(objetos);
	}

	public ResponseEntity<CategoriaDespesa> getByCodigo(@PathVariable Long codigo) {
		CategoriaDespesa objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
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

	private CategoriaDespesa atualizar(Long codigo, CategoriaDespesa objetoModificado)
			throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

}
