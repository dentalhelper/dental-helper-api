package com.projeto.dentalhelper.resources.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Pagamento;

@RequestMapping(value = "pagamentos")
public interface PagamentoApi {
	
	@GetMapping
	public List<Pagamento> getAll();

}
