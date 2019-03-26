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
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.dentalhelper.domains.Procedimento;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/procedimentos")
public interface ProcedimentoApi {
	
	@ApiOperation(value="Salva um procedimento")
	@PostMapping(value = "/novo")
	public ResponseEntity<Procedimento> post(@Valid @RequestBody Procedimento objeto,
			HttpServletResponse response);

	@ApiOperation(value="Busca todos os procedimentos ou procedimentos filtrados pelo nome")
	@GetMapping
	public List<Procedimento> getByNome(@RequestParam(required = false, defaultValue = "%") String nome);
	
	@ApiOperation(value="Busca um procedimento por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Procedimento> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value="Atualiza um procedimento")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Procedimento> put(@PathVariable Long codigo,
			@Valid @RequestBody Procedimento objeto);
	
	@ApiOperation(value="Deleta um procedimento")
	@ApiResponses(value = {
//			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
