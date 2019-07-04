package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.ImagemAnexada;
import com.projeto.dentalhelper.repositories.ImagemAnexada.ImagemAnexadaRepositoryQuery;

@Repository
public interface ImagemAnexadaRepository extends JpaRepository<ImagemAnexada, Long>, ImagemAnexadaRepositoryQuery{

}
