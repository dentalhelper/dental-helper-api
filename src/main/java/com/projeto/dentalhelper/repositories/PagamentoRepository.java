package com.projeto.dentalhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.repositories.Pagamento.PagamentoRepositoryQuery;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>, PagamentoRepositoryQuery{

}
