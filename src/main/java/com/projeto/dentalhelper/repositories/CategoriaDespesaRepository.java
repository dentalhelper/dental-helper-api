package com.projeto.dentalhelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.CategoriaDespesa;

@Repository
public interface CategoriaDespesaRepository extends JpaRepository<CategoriaDespesa, Long> {
	List<CategoriaDespesa> findByNomeEquals(String nome);
}
