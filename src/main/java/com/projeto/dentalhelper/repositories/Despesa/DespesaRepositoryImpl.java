package com.projeto.dentalhelper.repositories.Despesa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;

public class DespesaRepositoryImpl implements DespesaRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Despesa> filtrar(DespesaFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Despesa> criteria = builder.createQuery(Despesa.class);
		Root<Despesa> root = criteria.from(Despesa.class);
		
		//adicionando as restrições para os filtros
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Despesa> query = manager.createQuery(criteria);
		return query.getResultList();
		
	}

	private Predicate[] criarRestricoes(DespesaFilter filter, CriteriaBuilder builder, Root<Despesa> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getCategoria() != null) {
			predicates.add(builder.like(builder.lower(root.join("categoria").<String>get("nome")), "%" + filter.getCategoria().toLowerCase() + "%"));
			
		}
		if(filter.getDataPrevistaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataPrevista"), filter.getDataPrevistaDe()));
		}
		if(filter.getDataPrevistaAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataPrevista"), filter.getDataPrevistaAte()));
		}
		if(filter.getDataRealizadaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataRealizada"), filter.getDataRealizadaDe()));
		}
		if(filter.getDataRealizadaAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataRealizada"), filter.getDataRealizadaAte()));
		}
		if(filter.getDescricao() != null) {
			predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%"));
		}
			
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
