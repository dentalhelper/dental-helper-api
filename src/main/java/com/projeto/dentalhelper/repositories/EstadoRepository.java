package com.projeto.dentalhelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dentalhelper.domains.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Transactional(readOnly = true)
	public List<Estado> findAllByOrderByNome();
}
