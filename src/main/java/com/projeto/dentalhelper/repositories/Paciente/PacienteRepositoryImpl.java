package com.projeto.dentalhelper.repositories.Paciente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;

public class PacienteRepositoryImpl implements PacienteRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Paciente> buscarPorCpf(PacienteFilter filter) {
		
		filter.setNome(null);
		filter.setRg(null);
		
		return filtrar(filter);
		
	}
	
	@Override
	public List<Paciente> buscarPorRg(PacienteFilter filter) {
		
		filter.setNome(null);
		filter.setCpf(null);
		
		return filtrar(filter);
		
	}
	
	@Override
	public List<Paciente> filtrar(PacienteFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Paciente> criteria = builder.createQuery(Paciente.class);
		Root<Paciente> root = criteria.from(Paciente.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Paciente> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	
	
	
	private Predicate[] criarRestricoes(PacienteFilter filter, CriteriaBuilder builder, Root<Paciente> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		
		if(filter.getCpf() != null) {
			predicates.add(builder.equal(root.get("cPF"), filter.getCpf()));
		}
		
		if(filter.getRg() != null) {
			predicates.add(builder.equal(root.get("rG"), filter.getRg()));
		}
		
		if(filter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
