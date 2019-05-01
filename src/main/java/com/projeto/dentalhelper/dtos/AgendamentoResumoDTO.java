package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Agendamento;

public class AgendamentoResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigoAgendamento;
	
	private Date dataAgendamento;
	
	private String horaInicio;
	
	private String horaFim;
	
	private Boolean primeiraAvalicao;
	
	private Long codigoPaciente;
	
	private String nomeProcedimento;
	
	private String statusAgendamento;
	
	
	public AgendamentoResumoDTO(Agendamento agendamento) {
		this.codigoAgendamento = agendamento.getCodigo();
		this.dataAgendamento = agendamento.getDataAgendamento();
		this.horaInicio = converterHoraParaString(agendamento.getHoraInicio());
		this.horaFim = converterHoraParaString(agendamento.getHoraFim());
		this.primeiraAvalicao = agendamento.getPrimeiraAvalicao();
		this.codigoPaciente = agendamento.getPaciente().getCodigo();
		this.nomeProcedimento = agendamento.getProcedimento().getNome();
		this.statusAgendamento = agendamento.getStatusAgendamento().getDescricao();
	}
	
	

	public Long getCodigoAgendamento() {
		return codigoAgendamento;
	}

	public void setCodigoAgendamento(Long codigoAgendamento) {
		this.codigoAgendamento = codigoAgendamento;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public Boolean getPrimeiraAvalicao() {
		return primeiraAvalicao;
	}

	public void setPrimeiraAvalicao(Boolean primeiraAvalicao) {
		this.primeiraAvalicao = primeiraAvalicao;
	}

	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}
	
	
	
	public String getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(String statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	private String converterHoraParaString(Date hora) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
        return dateFormat.format(hora);  
	}
	
	

}
