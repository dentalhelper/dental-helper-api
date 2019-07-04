package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Dente;

@Repository
public interface DenteRepository extends JpaRepository<Dente, Long>{

}
