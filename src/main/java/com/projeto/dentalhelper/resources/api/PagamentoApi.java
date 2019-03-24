package com.projeto.dentalhelper.resources.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Pagamento;

import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "pagamentos")
public interface PagamentoApi {
	
	@ApiOperation(value="Busca todos os pagamentos")
	@GetMapping
	public List<Pagamento> getAll();

}
