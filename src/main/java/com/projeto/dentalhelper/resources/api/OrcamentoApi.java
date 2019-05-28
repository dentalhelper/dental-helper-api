package com.projeto.dentalhelper.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.dtos.OrcamentoNovoDTO;
import com.projeto.dentalhelper.dtos.OrcamentoResumoDTO;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/orcamentos")
public interface OrcamentoApi {

	@ApiOperation(value = "Salva um orçamento")
	@PostMapping(value = "/novo")
	public ResponseEntity<Orcamento> post(@Valid @RequestBody OrcamentoNovoDTO objeto, HttpServletResponse response)
			throws ServiceApplicationException;

	@ApiOperation(value = "Buscar orçamentos por filtro")
	@GetMapping
	public ResponseEntity<List<OrcamentoResumoDTO>> getByFilter(OrcamentoFilter filt);

	@ApiOperation(value = "Busca um Orcamento por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Orcamento> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value = "Busca dados do Orcamento pelo código")
	@GetMapping(value = "/{codigo}/edit")
	public ResponseEntity<OrcamentoNovoDTO> getByCodigoForEdit(@PathVariable Long codigo);

	@ApiOperation(value = "Atualiza um Orcamento")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Orcamento> put(@PathVariable Long codigo,
			@Valid @RequestBody OrcamentoNovoDTO objetoModificado) throws ServiceApplicationException;

	@ApiOperation(value = "Deleta um Orcamento")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
	
	@ApiOperation(value = "Atualiza o status de um orçamento")
	@PatchMapping(value = "/{codigo}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orcamento> atualizarStatus(@PathVariable Long codigo, @RequestBody String aprovado) throws ServiceApplicationException;

	
	@ApiOperation(value = "Atualiza o status do pagamento de um orçamento para cancelado")
	@PatchMapping(value = "/{codigo}/pagamento")
	public ResponseEntity<Orcamento> atualizarStatusPagamento(@PathVariable Long codigo) throws ServiceApplicationException;

}
