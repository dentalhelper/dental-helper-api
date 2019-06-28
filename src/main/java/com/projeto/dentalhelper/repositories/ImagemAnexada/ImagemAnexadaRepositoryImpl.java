package com.projeto.dentalhelper.repositories.ImagemAnexada;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projeto.dentalhelper.domains.ImagemAnexada;

public class ImagemAnexadaRepositoryImpl implements ImagemAnexadaRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<ImagemAnexada> buscarPorCodigoDeProcedimentoPrevisto(Long codigo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ImagemAnexada> criteria = builder.createQuery(ImagemAnexada.class);
		Root<ImagemAnexada> root = criteria.from(ImagemAnexada.class);
		
		//adicionando as restrições para os filtros
		List<Predicate> predicatesArray = new ArrayList<>();
		
		if(codigo != null) {
			predicatesArray.add(builder.equal(root.join("procedimentoPrevisto").<Long>get("codigo"), codigo));
		}
		
		Predicate[] predicates = predicatesArray.toArray(new Predicate[predicatesArray.size()]);
		
		criteria.where(predicates);
		
		TypedQuery<ImagemAnexada> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	



}
