package com.projeto.dentalhelper.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.resources.api.ProcedimentoPrevistoApi;
import com.projeto.dentalhelper.services.ProcedimentoPrevistoService;

@RestController
public class ProcedimentoPrevistoResource extends AbstractResource<ProcedimentoPrevisto, ProcedimentoPrevistoService> implements ProcedimentoPrevistoApi{

	@Override
	public ResponseEntity<ProcedimentoPrevisto> atualizarFinalizacao(Long codigo){
		return ResponseEntity.ok(service.alterarFinalizado(codigo));
	}

}
