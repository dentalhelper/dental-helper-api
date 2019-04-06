package com.projeto.dentalhelper.resources.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.dtos.CidadeDTO;
import com.projeto.dentalhelper.dtos.EstadoDTO;

import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/estados")
public interface EstadoAPI {

	@ApiOperation(value = "Busca todos os Estados.")
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> getAll();

	@ApiOperation(value = "Busca as Cidades de um Estado.")
	@GetMapping(value = "/{codigoEstado}/cidades")
	public ResponseEntity<List<CidadeDTO>> getCidades(@PathVariable Long codigoEstado);
}
