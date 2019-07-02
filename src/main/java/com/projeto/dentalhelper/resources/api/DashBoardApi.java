package com.projeto.dentalhelper.resources.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.dtos.DashBoardDTO;

import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/dashboard")
public interface DashBoardApi {
	
	
	@ApiOperation(value="Busca as informações da dashboard")
	@GetMapping
	public ResponseEntity<DashBoardDTO> getInformacaoes();
	
	

}
