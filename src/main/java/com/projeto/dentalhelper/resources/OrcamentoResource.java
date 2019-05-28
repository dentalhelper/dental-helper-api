package com.projeto.dentalhelper.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.enums.StatusPagamento;
import com.projeto.dentalhelper.dtos.OrcamentoNovoDTO;
import com.projeto.dentalhelper.dtos.OrcamentoResumoDTO;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;
import com.projeto.dentalhelper.resources.api.OrcamentoApi;
import com.projeto.dentalhelper.services.OrcamentoService;
import com.projeto.dentalhelper.services.exceptions.OrcamentoDeveConterProcedimentoException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoDeveConterProcedimentoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class OrcamentoResource extends AbstractResource<Orcamento, OrcamentoService> implements OrcamentoApi {

	@Override
	public ResponseEntity<Orcamento> post(@Valid OrcamentoNovoDTO objeto, HttpServletResponse response) {
		Orcamento objetoSalvo = null;

		try {
			objetoSalvo = service.fromDTO(objeto);
			objetoSalvo = salvar(objetoSalvo);
		} catch (OrcamentoDeveConterProcedimentoException e) {
			throw new OrcamentoDeveConterProcedimentoRuntimeException(e.getMessage());
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
	public ResponseEntity<List<OrcamentoResumoDTO>> getByFilter(OrcamentoFilter filter) {
		List<Orcamento> objetos = service.filtrar(filter);
		adicionarLinks(objetos);
		List<OrcamentoResumoDTO> orcamentoResumoDTO = objetos.stream()
				.map(orcamento -> new OrcamentoResumoDTO(orcamento)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(orcamentoResumoDTO);
	}

	@Override
	public ResponseEntity<Orcamento> put(Long codigo, @Valid OrcamentoNovoDTO objetoModificado)
			throws ServiceApplicationException {
		Orcamento objetoEditado = null;
		try {
			objetoEditado = service.fromDTO(objetoModificado);
			objetoEditado = atualizar(codigo, objetoEditado);
		} catch (OrcamentoDeveConterProcedimentoException e) {
			throw new OrcamentoDeveConterProcedimentoRuntimeException(e.getMessage());
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

	@Override
	public ResponseEntity<OrcamentoNovoDTO> getByCodigoForEdit(Long codigo) {
		Orcamento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		OrcamentoNovoDTO orcamentoDTO = new OrcamentoNovoDTO(objeto);
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(orcamentoDTO);
	}

	@Override
	public ResponseEntity<Orcamento> atualizarStatus(@PathVariable Long codigo, @RequestBody String aprovado)
			throws ServiceApplicationException {
		Orcamento orcamento = service.atualizarStatus(codigo, aprovado);
		
		
		return ResponseEntity.ok(orcamento);
	}

	@Override
	public ResponseEntity<Orcamento> atualizarStatusPagamento(Long codigo) throws ServiceApplicationException {
		Orcamento orcamento = service.atualizarStatusPagamento(codigo, StatusPagamento.CANCELADO.getCodigo());
		
		
		return ResponseEntity.ok(orcamento);
	}



}
