package com.projeto.dentalhelper.repositories.orcamento;

import java.util.List;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;

public interface OrcamentoRepositoryQuery {

	public List<Orcamento> filtrar(OrcamentoFilter filter);
}
