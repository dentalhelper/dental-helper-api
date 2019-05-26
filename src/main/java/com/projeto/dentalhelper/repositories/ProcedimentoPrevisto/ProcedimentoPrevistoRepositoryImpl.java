package com.projeto.dentalhelper.repositories.ProcedimentoPrevisto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.repositories.filter.ProcedimentoPrevistoFilter;

public class ProcedimentoPrevistoRepositoryImpl implements ProcedimentoPrevistoRepositoryQuery{
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<ProcedimentoPrevisto> filtrar(ProcedimentoPrevistoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ProcedimentoPrevisto> criteria = builder.createQuery(ProcedimentoPrevisto.class);
		Root<ProcedimentoPrevisto> root = criteria.from(ProcedimentoPrevisto.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ProcedimentoPrevisto> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ProcedimentoPrevistoFilter filter, CriteriaBuilder builder, Root<ProcedimentoPrevisto> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		if(filter.getCodigoPaciente() != null) {
			predicates.add(builder.equal(root.join("orcamento").join("paciente").<Long>get("codigo"), filter.getCodigoPaciente()));
		}
		
		if(filter.getFinalizado() != null) {
			predicates.add(builder.equal(root.get("finalizado"), filter.getFinalizado()));
		}
			
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
