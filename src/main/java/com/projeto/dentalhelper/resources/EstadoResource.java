package com.projeto.dentalhelper.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Cidade;
import com.projeto.dentalhelper.domains.Estado;
import com.projeto.dentalhelper.dtos.CidadeDTO;
import com.projeto.dentalhelper.dtos.EstadoDTO;
import com.projeto.dentalhelper.resources.api.EstadoAPI;
import com.projeto.dentalhelper.services.CidadeService;
import com.projeto.dentalhelper.services.EstadoService;

@RestController
public class EstadoResource implements EstadoAPI {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Override
	public ResponseEntity<List<EstadoDTO>> getAll() {
		List<Estado> estados = service.buscarTodos();
		List<EstadoDTO> listaEstadosDTO = estados.stream().map(objeto -> new EstadoDTO(objeto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaEstadosDTO);
	}

	@Override
	public ResponseEntity<List<CidadeDTO>> getCidades(@PathVariable  Long codigoEstado) {
		List<Cidade> cidades = cidadeService.buscarPorEstado(codigoEstado);
		List<CidadeDTO> listaCidadesDTO = cidades.stream().map(objeto -> new CidadeDTO(objeto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCidadesDTO);
	}

}
