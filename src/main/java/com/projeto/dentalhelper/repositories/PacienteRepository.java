package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.dentalhelper.domains.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
