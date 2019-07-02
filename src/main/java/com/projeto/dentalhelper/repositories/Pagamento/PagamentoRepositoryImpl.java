package com.projeto.dentalhelper.repositories.Pagamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.repositories.filter.PagamentoFilter;

public class PagamentoRepositoryImpl implements PagamentoRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Pagamento> filtrar(PagamentoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pagamento> criteria = builder.createQuery(Pagamento.class);
		Root<Pagamento> root = criteria.from(Pagamento.class);
		
		//adicionando as restrições para os filtros
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Pagamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(PagamentoFilter filter, CriteriaBuilder builder, Root<Pagamento> root) {
List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getDataPagamento() != null) {
			predicates.add(builder.equal(root.get("dataPagamento"), filter.getDataPagamento()));
		}
		if(filter.getTipo() != null) {
			predicates.add(builder.equal(root.get("tipo"), filter.getTipo()));
		}
			
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
