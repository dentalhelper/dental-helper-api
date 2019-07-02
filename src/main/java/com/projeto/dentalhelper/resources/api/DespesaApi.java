package com.projeto.dentalhelper.resources.api;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.dtos.DespesaRecebimentoDashBoardDTO;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/despesas")
public interface DespesaApi {

	@ApiOperation(value = "Salva uma despesa com pagamento")
	@PostMapping(value = "/novo")
	public ResponseEntity<Despesa> post(@Valid @RequestBody Despesa objeto, HttpServletResponse response)
			throws ServiceApplicationException;

	@ApiOperation(value = "Busca despesas por filtros")
	@GetMapping
	public List<Despesa> getByFilter(DespesaFilter filter);

	@ApiOperation(value = "Busca uma despesa por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Despesa> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value = "Atualiza uma despesa e seu pagamento")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Despesa> put(@PathVariable Long codigo, @Valid @RequestBody Despesa objeto)
			throws ServiceApplicationException;

	@ApiOperation(value = "Deleta uma despesa e seu pagamento")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'ASSISTENTE')")
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
	
	
	@ApiOperation(value = "Busca despesas por data para dashboard")
	@GetMapping("/dashboard")
	public ResponseEntity<List<DespesaRecebimentoDashBoardDTO>> filtrarparaDashBoard(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd")Date data);

}
