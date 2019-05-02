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

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.dtos.AgendamentoResumoDTO;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/agendamentos")
public interface AgendamentoApi {

	@ApiOperation(value = "Salva um agendamento")
	@PostMapping(value = "/novo")
	public ResponseEntity<Agendamento> post(@Valid @RequestBody AgendamentoNovoDTO objeto, HttpServletResponse response)
			throws ServiceApplicationException;

	@ApiOperation(value = "Busca agendamentos por filtro")
	@GetMapping
	public ResponseEntity<List<AgendamentoResumoDTO>> getByFilter(AgendamentoFilter filter);

	@ApiOperation(value = "Busca um agendamento por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Agendamento> getByCodigo(@PathVariable Long codigo);
	
	@ApiOperation(value = "Busca dados do agendamento pelo código")
	@GetMapping(value = "/{codigo}/edit")
	public ResponseEntity<AgendamentoNovoDTO> getByCodigoForEdit(@PathVariable Long codigo);
	

	@ApiOperation(value = "Atualiza um agendamento")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Agendamento> put(@PathVariable Long codigo,
			@Valid @RequestBody AgendamentoNovoDTO objetoModificado) throws ServiceApplicationException;

	@ApiOperation(value = "Deleta uma categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
