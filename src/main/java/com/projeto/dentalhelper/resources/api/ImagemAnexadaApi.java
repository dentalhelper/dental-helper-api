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

import com.projeto.dentalhelper.domains.Foto;
import com.projeto.dentalhelper.domains.ImagemAnexada;
import com.projeto.dentalhelper.dtos.ImagensAnexadasNovoDTO;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "imagensAnexadas")
public interface ImagemAnexadaApi {
	
	@ApiOperation(value="Busca imagens anexadas pelo código do procedimento previsto")
	@GetMapping
	public List<ImagemAnexada> getByCodigoProcedimentoPrevisto(@RequestParam(required = false) Long codigo);
	
	@ApiOperation(value = "Salva a imagem")
	@PostMapping(value = "/{foto}")
	public ResponseEntity<Foto> postImage(@RequestParam(name = "file") MultipartFile file);
	
	
	@ApiOperation(value = "Salva uma lista de imagens anexadas")
	@PostMapping(value = "/novo")
	public ResponseEntity<List<ImagemAnexada>> post(@Valid @RequestBody ImagensAnexadasNovoDTO objetoDTO, HttpServletResponse response)
			throws ServiceApplicationException;
	
	@ApiOperation(value = "Busca uma imagem anexada pelo código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<ImagemAnexada> getByCodigo(@PathVariable Long codigo);
	
	@ApiOperation(value = "Atualiza a descrição de uma imagem pelo código")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<ImagemAnexada> put(@PathVariable Long codigo,
			@Valid @RequestBody String descricao) throws ServiceApplicationException;
	
	@ApiOperation(value = "Deleta uma imagem anexada")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
