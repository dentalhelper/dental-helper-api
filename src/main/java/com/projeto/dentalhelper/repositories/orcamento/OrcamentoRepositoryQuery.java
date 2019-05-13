package com.projeto.dentalhelper.repositories.orcamento;

import java.util.List;

import com.projeto.dentalhelper.domains.Orcamento;

public interface OrcamentoRepositoryQuery {

	public List<Orcamento> buscarPorCodigoPaciente(Long codigo);
}
