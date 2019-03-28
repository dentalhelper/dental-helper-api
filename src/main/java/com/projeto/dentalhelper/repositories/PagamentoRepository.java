package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.dentalhelper.domains.Pagamento;


public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
