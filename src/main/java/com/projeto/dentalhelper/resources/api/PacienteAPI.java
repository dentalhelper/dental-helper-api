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

import com.projeto.dentalhelper.domains.Paciente;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/pacientes")
public interface PacienteAPI {
	
	@ApiOperation(value="Salva um paciente")
	@PostMapping(value = "/novo")
	public ResponseEntity<Paciente> post(@Valid @RequestBody Paciente objeto,
			HttpServletResponse response);
	
	@ApiOperation(value="Busca pacientes pelo nome ou um paciente pelo cpf")
	@GetMapping
	public List<Paciente> getByFilter(@RequestParam(required = false, defaultValue = "%") String filtro);
	
	@ApiOperation(value="Busca um paciente por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Paciente> getByCodigo(@PathVariable Long codigo);
	
	@ApiOperation(value="Atualiza um paciente")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity <Paciente> put(@PathVariable Long codigo,@Valid @RequestBody Paciente objeto);
	
	@ApiOperation(value="Deleta um paciente")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
