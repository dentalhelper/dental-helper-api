package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.ImagemAnexada;

@Repository
public interface ImagemAnexadaRepository extends JpaRepository<ImagemAnexada, Long>{

}
