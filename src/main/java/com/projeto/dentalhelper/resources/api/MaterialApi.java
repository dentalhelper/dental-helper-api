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

import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/materiais")
public interface MaterialApi {

	@ApiOperation(value = "Salva um material")
	@PostMapping(value = "/novo")
	public ResponseEntity<Material> post(@Valid @RequestBody Material objeto, HttpServletResponse response)
			throws ServiceApplicationException;

	@ApiOperation(value = "Busca materiais por filtro")
	@GetMapping
	public List<Material> getByFilter(MaterialFilter filter);

	@ApiOperation(value = "Busca um material por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Material> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value = "Atualiza um material e seus atributos")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Material> put(@PathVariable Long codigo, @Valid @RequestBody Material objeto)
			throws ServiceApplicationException;

	@ApiOperation(value = "Deleta um material com seus atributos")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
}
