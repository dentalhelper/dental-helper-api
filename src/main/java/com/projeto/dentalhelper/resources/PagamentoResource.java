package com.projeto.dentalhelper.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.repositories.PagamentoRepository;
import com.projeto.dentalhelper.resources.api.PagamentoApi;

@RestController
public class PagamentoResource implements PagamentoApi{

	@Autowired
	private PagamentoRepository repository;
	
	@Override
	public List<Pagamento> getAll() {
		return repository.findAll();
	}

}
