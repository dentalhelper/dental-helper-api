package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.Despesa.DespesaRepositoryQuery;

public interface DespesaRepository extends JpaRepository<Despesa, Long>, DespesaRepositoryQuery{

}
