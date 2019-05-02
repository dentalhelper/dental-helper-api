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
import org.springframework.web.multipart.MultipartFile;

import com.projeto.dentalhelper.domains.Anamnese;
import com.projeto.dentalhelper.domains.Foto;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.dtos.PacienteAgendamentoDTO;
import com.projeto.dentalhelper.dtos.PacienteAnamneseDTO;
import com.projeto.dentalhelper.dtos.PacienteNovoDTO;
import com.projeto.dentalhelper.dtos.PacienteResumoDTO;
import com.projeto.dentalhelper.dtos.PacienteSelectComFotoDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/pacientes")
public interface PacienteAPI {

	@ApiOperation(value = "Salva um paciente")
	@PostMapping(value = "/novo")
	public ResponseEntity<Paciente> post(@Valid @RequestBody PacienteNovoDTO objeto, HttpServletResponse response);

	@ApiOperation(value = "Salva a imagem do Paciente")
	@PostMapping(value = "/{foto}")
	public ResponseEntity<Foto> postImage(@RequestParam(name = "file") MultipartFile file);

	@ApiOperation(value = "Busca pacientes pelo nome ou um paciente pelo cpf")
	@GetMapping
	public ResponseEntity<List<PacienteResumoDTO>> getByFilter(
			@RequestParam(required = false, defaultValue = "%") String filtro);

	@ApiOperation(value = "Busca a imagem e o nome de todos os pacientes")
	@GetMapping(value = "/nome-foto")
	public ResponseEntity<List<PacienteSelectComFotoDTO>> getAllWithImage();

	@ApiOperation(value = "Busca um paciente por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Paciente> getByCodigo(@PathVariable Long codigo);

	@ApiOperation(value = "Busca dados cadastrais do paciente pelo código")
	@GetMapping(value = "/{codigo}/edit")
	public ResponseEntity<PacienteNovoDTO> getByCodigoForEdit(@PathVariable Long codigo);

	@ApiOperation(value = "Atualiza um paciente")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Paciente> put(@PathVariable Long codigo, @Valid @RequestBody PacienteNovoDTO objetoDTO);

	@ApiOperation(value = "Deleta um paciente")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que "
					+ "está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

	@ApiOperation(value = "Atualiza a anamnese do paciente")
	@PutMapping(value = "/{codigo}/anamnese")
	public ResponseEntity<Paciente> put(@PathVariable Long codigo, @Valid @RequestBody Anamnese anamnese);

	@ApiOperation(value = "Busca um paciente pelo código e retorna sua anamnese")
	@GetMapping(value = "/{codigo}/anamnese")
	public ResponseEntity<PacienteAnamneseDTO> getAnamneseByCodigoPaciente(@PathVariable Long codigo);
	
	@ApiOperation(value = "Busca um paciente pelo código e retorna seus agendamentos")
	@GetMapping(value = "/{codigo}/agendamentos")
	public ResponseEntity<PacienteAgendamentoDTO> getAgendamentosByCodigoPaciente(@PathVariable Long codigo);

}
