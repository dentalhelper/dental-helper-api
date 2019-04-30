package com.projeto.dentalhelper.repositories.Agendamento;

import java.util.List;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;

public interface AgendamentoRepositoryQuery {
	
	//busca com o filtro completo
	public List<Agendamento> buscarPorFiltro(AgendamentoFilter filter);
	
	//filtra pela hora e data para verificar se já existe agendamento alocado nesses horário
	public List<Agendamento> buscarPorHoraEData(AgendamentoFilter filter);
	
	//filtra apenas pelo código do paciente
	public List<Agendamento> buscarPorCodigoPaciente(Long codigo);
	

}
