package com.projeto.dentalhelper.repositories.Agendamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;

public class AgendamentoRepositoryImpl implements AgendamentoRepositoryQuery{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Agendamento> buscarPorFiltro(AgendamentoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Agendamento> criteria = builder.createQuery(Agendamento.class);
		Root<Agendamento> root = criteria.from(Agendamento.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Agendamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	
	
	@Override
	public List<Agendamento> buscarPorCodigoPaciente(Long codigo) {
		AgendamentoFilter filter = new AgendamentoFilter();
		filter.setCodPaciente(codigo);
		
		return buscarPorFiltro(filter);
	}
	
	
	public List<Agendamento> filtrarPorHoraEData(AgendamentoFilter filter) {	
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Agendamento> criteria = builder.createQuery(Agendamento.class);
		Root<Agendamento> root = criteria.from(Agendamento.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Agendamento> query = manager.createQuery(criteria);
		List<Agendamento> resultado = query.getResultList();
		
		List<Agendamento> resultadoFinal = new ArrayList<Agendamento>();
		
		if(filter.getHoraInicioMin() != null && filter.getHoraInicioMax() != null) {
			try {
				resultadoFinal =  filtrarPorHoraJaAlocada(resultado, filter);
				return resultadoFinal;
			} catch (ParseException e) {}
		}
		
		return resultado;
	}
	
	

	private Predicate[] criarRestricoes(AgendamentoFilter filter, CriteriaBuilder builder, Root<Agendamento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		if(filter.getDataAgendamentoMin() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataAgendamento"), filter.getDataAgendamentoMin()));
		}
		if(filter.getDataAgendamentoMax() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataAgendamento"), filter.getDataAgendamentoMax()));
		}
		if(filter.getCodPaciente() != null) {
			predicates.add(builder.equal(root.join("paciente").<Long>get("codigo"), filter.getCodPaciente()));

		}
		if(filter.getDataAgendamento() != null) {
			predicates.add(builder.equal(root.get("dataAgendamento"), filter.getDataAgendamento()));
		}

			
		return predicates.toArray(new Predicate[predicates.size()]);
	}



	@Override
	public List<Agendamento> buscarPorHoraEData(AgendamentoFilter filter) {
		filter.setCodPaciente(null);
		filter.setDataAgendamentoMax(null);
		filter.setDataAgendamentoMin(null);
		
		return filtrarPorHoraEData(filter);
	}
	
	private List<Agendamento> filtrarPorHoraJaAlocada(List<Agendamento> agendamentos, AgendamentoFilter filter) throws ParseException {
		
		List<Agendamento> resultado = new ArrayList<Agendamento>();
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		
		if(filter.getHoraInicioMin() != null) {
			String horaInicialMinString =  dateFormat.format(filter.getHoraInicioMin());
			filter.setHoraInicioMin(dateFormat.parse(horaInicialMinString));
		}
		
		if(filter.getHoraInicioMax() != null) {
			String horaInicialMaxString =  dateFormat.format(filter.getHoraInicioMax());
			filter.setHoraInicioMax(dateFormat.parse(horaInicialMaxString));
		}
		
		for(Agendamento a: agendamentos) {
			
			String horaInicialString =  dateFormat.format(a.getHoraInicio());
			String horaFinalString =  dateFormat.format(a.getHoraFim());
			
			Date horaInicio = dateFormat.parse(horaInicialString);
			
			Date horaFim = dateFormat.parse(horaFinalString);
			

			if(horaInicio.getTime() >= filter.getHoraInicioMin().getTime() && horaInicio.getTime() <= filter.getHoraInicioMax().getTime() || 
					horaFim.getTime() >= filter.getHoraInicioMin().getTime() && horaFim.getTime() <= filter.getHoraInicioMax().getTime() ||
					horaInicio.getTime() < filter.getHoraInicioMin().getTime() && horaFim.getTime() > filter.getHoraInicioMax().getTime()) {
				
					resultado.add(a);

			}
		}
		
		return resultado;
	}



	
	
	
	
	

}
