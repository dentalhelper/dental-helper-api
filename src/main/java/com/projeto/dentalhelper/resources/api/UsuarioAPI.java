package com.projeto.dentalhelper.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.dtos.UsuarioNovoDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/usuarios")
public interface UsuarioAPI {

	@ApiOperation(value = "Salva um usuário")
	@PostMapping(value = "/novo")
//	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> post(@Valid @RequestBody UsuarioNovoDTO objeto, HttpServletResponse response);
	
	@ApiOperation(value = "Busca todos os usuários")
	@GetMapping
	public List<Usuario> getAll();

	@ApiOperation(value = "Busca um usuário por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Usuario> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Usuario> put(@PathVariable Long codigo, @Valid @RequestBody UsuarioNovoDTO objetoDTO);

	@ApiOperation(value = "Deleta um usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que "
					+ "está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
	
}
