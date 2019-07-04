package com.projeto.dentalhelper.dtos;

import java.util.ArrayList;
import java.util.List;

public class DashBoardDTO {
	
	
	
	private List<AgendamentoDashBoardDTO> agendamentos = new ArrayList<AgendamentoDashBoardDTO>();
	
	private List<DespesaRecebimentoDashBoardDTO> pagamentos = new ArrayList<DespesaRecebimentoDashBoardDTO>();
	
	private List<RecebimentoDespesaDashBoardGraficoDTO> pagamentosGrafico = new ArrayList<RecebimentoDespesaDashBoardGraficoDTO>();
	
	
	
	


	
	public DashBoardDTO(List<AgendamentoDashBoardDTO> agendamentos, List<DespesaRecebimentoDashBoardDTO> pagamentos,
			List<RecebimentoDespesaDashBoardGraficoDTO> pagamentosGrafico) {
		this.agendamentos = agendamentos;
		this.pagamentos = pagamentos;
		this.pagamentosGrafico = pagamentosGrafico;
	}

	public DashBoardDTO() {

	}

	public List<AgendamentoDashBoardDTO> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoDashBoardDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<DespesaRecebimentoDashBoardDTO> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<DespesaRecebimentoDashBoardDTO> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public List<RecebimentoDespesaDashBoardGraficoDTO> getPagamentosGrafico() {
		return pagamentosGrafico;
	}

	public void setPagamentosGrafico(List<RecebimentoDespesaDashBoardGraficoDTO> pagamentosGrafico) {
		this.pagamentosGrafico = pagamentosGrafico;
	}
	
	
	
	
	

}
