package com.projeto.dentalhelper.repositories.orcamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;

public class OrcamentoRepositoryImpl implements OrcamentoRepositoryQuery {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Orcamento> buscarPorCodigoPaciente(Long codigo) {
		OrcamentoFilter filter = new OrcamentoFilter();
		filter.setCodigoPaciente(codigo);

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Orcamento> criteria = builder.createQuery(Orcamento.class);
		Root<Orcamento> root = criteria.from(Orcamento.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Orcamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(OrcamentoFilter filter, CriteriaBuilder builder, Root<Orcamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (filter.getCodigoPaciente() != null) {
			predicates.add(
					builder.equal(root.join("paciente").get("codigo"), filter.getCodigoPaciente()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
