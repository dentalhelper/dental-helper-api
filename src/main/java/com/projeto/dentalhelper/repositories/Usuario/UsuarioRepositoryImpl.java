package com.projeto.dentalhelper.repositories.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.repositories.filter.UsuarioFilter;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Usuario> buscarPorCpf(UsuarioFilter filter) {
		
		filter.setNome(null);
		filter.setRg(null);
		
		return filtrar(filter);
		
	}
	
	@Override
	public List<Usuario> buscarPorRg(UsuarioFilter filter) {
		
		filter.setNome(null);
		filter.setCpf(null);
		
		return filtrar(filter);
		
	}
	
	@Override
	public List<Usuario> filtrar(UsuarioFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	
	
	
	private Predicate[] criarRestricoes(UsuarioFilter filter, CriteriaBuilder builder, Root<Usuario> root) {

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
		
		if(filter.getLogin() != null) {
			predicates.add(builder.equal(root.get("login"), filter.getLogin()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<Usuario> buscarPorLogin(String login) {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setLogin(login);
		
		return filtrar(filter);
	}
	
	

}
