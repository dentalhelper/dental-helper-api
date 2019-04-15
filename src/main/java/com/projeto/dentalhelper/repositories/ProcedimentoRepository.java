package com.projeto.dentalhelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>{
	public List<Procedimento> findByNomeEquals(String nome);
	
	public List<Procedimento> findByNomeContaining(String nome);
}
