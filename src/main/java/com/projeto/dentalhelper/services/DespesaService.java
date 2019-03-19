package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.DespesaRepository;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;

@Service
public class DespesaService extends AbstractService<Despesa, DespesaRepository>{
	
	public List<Despesa> filtrar(DespesaFilter filter){
		
		return repository.filtrar(filter);
		
	}

}
