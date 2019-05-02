package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.repositories.Agendamento.AgendamentoRepositoryQuery;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long>, AgendamentoRepositoryQuery{

}
