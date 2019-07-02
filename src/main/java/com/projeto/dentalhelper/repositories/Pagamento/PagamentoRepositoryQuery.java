package com.projeto.dentalhelper.repositories.Pagamento;

import java.util.List;

import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.repositories.filter.PagamentoFilter;

public interface PagamentoRepositoryQuery {
	public List<Pagamento> filtrar (PagamentoFilter filter);
}
