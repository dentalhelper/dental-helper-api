package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.repositories.orcamento.OrcamentoRepositoryQuery;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>, OrcamentoRepositoryQuery {

}
