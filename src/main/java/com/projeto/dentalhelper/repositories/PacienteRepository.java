package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.repositories.Paciente.PacienteRepositoryQuery;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, PacienteRepositoryQuery{
	

}
