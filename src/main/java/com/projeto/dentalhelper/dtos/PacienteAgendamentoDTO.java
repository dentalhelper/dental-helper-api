package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Agendamento;

public class PacienteAgendamentoDTO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long codigoPaciente;
	
	private List<AgendamentoNovoDTO> agendamentos;
	
	public PacienteAgendamentoDTO(List<AgendamentoNovoDTO> agendamentos, Long codigoPaciente) {
		this.agendamentos = agendamentos;
		this.codigoPaciente = codigoPaciente;
	}
	
	
	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}


	public List<AgendamentoNovoDTO> getAgendamentos() {
		return agendamentos;
	}


	public void setAgendamentos(List<AgendamentoNovoDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}



}
