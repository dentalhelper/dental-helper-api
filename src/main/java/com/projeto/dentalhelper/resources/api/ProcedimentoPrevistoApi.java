package com.projeto.dentalhelper.resources.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/procedimentosPrevistos")
public interface ProcedimentoPrevistoApi {
	
	@ApiOperation(value = "Atualiza o atributo finalizado do procedimento previsto")
	@PatchMapping(value = "/{codigo}")
	public ResponseEntity<ProcedimentoPrevisto> atualizarFinalizacao(@PathVariable Long codigo) throws ServiceApplicationException;

}
