package com.projeto.dentalhelper.repositories.Agendamento;

import java.util.List;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;

public interface AgendamentoRepositoryQuery {
	
	
	public List<Agendamento> filtrar(AgendamentoFilter filter);
	
	public List<Agendamento> buscarPorHoraEData(AgendamentoFilter filter);
	

}
