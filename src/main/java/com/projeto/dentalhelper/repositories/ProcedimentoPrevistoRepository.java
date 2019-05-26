package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.repositories.ProcedimentoPrevisto.ProcedimentoPrevistoRepositoryQuery;

@Repository
public interface ProcedimentoPrevistoRepository extends JpaRepository<ProcedimentoPrevisto, Long>, ProcedimentoPrevistoRepositoryQuery{

}
