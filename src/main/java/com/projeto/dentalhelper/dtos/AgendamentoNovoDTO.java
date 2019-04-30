package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.projeto.dentalhelper.domains.Agendamento;

public class AgendamentoNovoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Date dataAgendamento;
	
	@NotBlank
	@Pattern(regexp="^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")
	private String horaInicio;
	

	@Pattern(regexp="^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")
	private String horaFim;
	
	@NotNull
	private Integer statusAgendamento;
	
	private String observacao;
	
	@NotNull
	private Boolean primeiraAvalicao;
	
	@NotNull
	private Long codigoPaciente;
	
	@NotNull
	private Long codigoProcedimento;
	
	public AgendamentoNovoDTO() {
	}
	
	public AgendamentoNovoDTO(Agendamento agendamento) {
		this.dataAgendamento = agendamento.getDataAgendamento();
		this.horaInicio = converterHoraParaString(agendamento.getHoraInicio());
		this.horaFim = converterHoraParaString(agendamento.getHoraFim());
		this.statusAgendamento = agendamento.getStatusAgendamento().getCodigo();
		this.observacao = agendamento.getObservacao();
		this.primeiraAvalicao = agendamento.getPrimeiraAvalicao();
		this.codigoPaciente = agendamento.getPaciente().getCodigo();
		this.codigoProcedimento = agendamento.getProcedimento().getCodigo();
	}
	
	


	public Integer getStatusAgendamento() {
		return statusAgendamento;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getPrimeiraAvalicao() {
		return primeiraAvalicao;
	}

	public void setPrimeiraAvalicao(Boolean primeiraAvalicao) {
		this.primeiraAvalicao = primeiraAvalicao;
	}

	public void setStatusAgendamento(Integer statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}
	
	public Long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(Long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	private String converterHoraParaString(Date hora) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        return dateFormat.format(hora);  
	}
	
	
}
