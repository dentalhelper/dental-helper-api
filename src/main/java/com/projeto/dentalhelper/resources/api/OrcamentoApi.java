package com.projeto.dentalhelper.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.dtos.OrcamentoResumoDTO;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/orcamentos")
public interface OrcamentoApi {

	@ApiOperation(value = "Salva um orçamento")
	@PostMapping(value = "/novo")
	public ResponseEntity<Orcamento> post(@Valid @RequestBody Orcamento objeto, HttpServletResponse response)
			throws ServiceApplicationException;

	@ApiOperation(value = "Busca todos os orçamentos")
	@GetMapping
	public ResponseEntity<List<OrcamentoResumoDTO>> getAll();

	@ApiOperation(value = "Busca um Orcamento por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Orcamento> getByCodigo(@PathVariable Long codigo);	

	@ApiOperation(value = "Atualiza um Orcamento")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Orcamento> put(@PathVariable Long codigo,
			@Valid @RequestBody Orcamento objetoModificado) throws ServiceApplicationException;

	@ApiOperation(value = "Deleta um Orcamento")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
