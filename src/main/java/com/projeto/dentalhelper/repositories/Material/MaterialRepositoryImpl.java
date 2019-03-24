package com.projeto.dentalhelper.repositories.Material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;

public class MaterialRepositoryImpl implements MaterialRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Material> filtrar(MaterialFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Material> criteria = builder.createQuery(Material.class);
		Root<Material> root = criteria.from(Material.class);
		
		//adicionando as restrições para os filtros
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Material> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(MaterialFilter filter, CriteriaBuilder builder, Root<Material> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		
		if(filter.getFabricante() != null) {
			predicates.add(builder.like(builder.lower(root.get("fabricante")), "%" + filter.getFabricante().toLowerCase() + "%"));
		}
		if(filter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
		}
		if(filter.getValorAtributo() != null) {
			predicates.add(builder.like(builder.lower(root.join("atributoMateriais").<String>get("valor")), "%" + filter.getValorAtributo().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
