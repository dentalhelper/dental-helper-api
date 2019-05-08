package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{

}
