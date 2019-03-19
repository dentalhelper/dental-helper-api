package com.projeto.dentalhelper.repositories.Despesa;

import java.util.List;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;

public interface DespesaRepositoryQuery {
	
	
	public List<Despesa> filtrar(DespesaFilter filter);
	
}
