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

import com.projeto.dentalhelper.domains.CategoriaDespesa;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/categorias-despesa")
public interface CategoriaDespesaApi {

	@ApiOperation(value="Salva uma categoria")
	@PostMapping(value = "/novo")
	public ResponseEntity<CategoriaDespesa> post(@Valid @RequestBody CategoriaDespesa objeto,
			HttpServletResponse response);

	@ApiOperation(value="Busca todas as categorias")
	@GetMapping
	public List<CategoriaDespesa> getAll();
	
	@ApiOperation(value="Busca uma categoria por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDespesa> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value="Atualiza uma categoria")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDespesa> put(@PathVariable Long codigo,
			@Valid @RequestBody CategoriaDespesa objeto);
	
	@ApiOperation(value="Deleta uma categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
}
