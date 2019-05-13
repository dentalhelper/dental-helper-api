package com.projeto.dentalhelper.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.dtos.OrcamentoResumoDTO;
import com.projeto.dentalhelper.resources.api.OrcamentoApi;
import com.projeto.dentalhelper.services.OrcamentoService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class OrcamentoResource extends AbstractResource<Orcamento, OrcamentoService> implements OrcamentoApi {

	@Override
	public ResponseEntity<Orcamento> post(@Valid Orcamento objeto, HttpServletResponse response) {
		Orcamento objetoSalvo = null;

		try {
			objetoSalvo = salvar(objeto);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/orcamentos");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private Orcamento salvar(Orcamento objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	@Override
	public ResponseEntity<List<OrcamentoResumoDTO>> getAll() {
		List<Orcamento> objetos = service.buscarTodos();
		adicionarLinks(objetos);
		List<OrcamentoResumoDTO> orcamentoResumoDTO = objetos.stream()
				.map(orcamento -> new OrcamentoResumoDTO(orcamento)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(orcamentoResumoDTO);
	}

	@Override
	public ResponseEntity<Orcamento> put(Long codigo, @Valid Orcamento objetoModificado)
			throws ServiceApplicationException {
		Orcamento objetoEditado = null;
		try {
			objetoEditado = atualizar(codigo, objetoModificado);
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
	}

	private Orcamento atualizar(Long codigo, Orcamento objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

	public ResponseEntity<Orcamento> getByCodigo(@PathVariable Long codigo) {
		Orcamento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

}
