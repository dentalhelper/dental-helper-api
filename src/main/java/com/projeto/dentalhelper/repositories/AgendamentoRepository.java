package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long>{

}
