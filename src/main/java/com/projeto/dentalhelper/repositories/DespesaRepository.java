package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.Despesa.DespesaRepositoryQuery;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>, DespesaRepositoryQuery{

	
}
