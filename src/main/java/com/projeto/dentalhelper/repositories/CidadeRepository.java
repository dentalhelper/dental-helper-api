package com.projeto.dentalhelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dentalhelper.domains.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT objeto FROM Cidade objeto WHERE objeto.estado.codigo = :codigoEstado ORDER BY objeto.nome")
	public List<Cidade> findCidades(@Param("codigoEstado") Long codigoEstado);
}
