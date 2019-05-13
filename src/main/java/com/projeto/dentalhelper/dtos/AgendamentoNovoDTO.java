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
	private Long codigoOrcamento;
	
	@NotNull
	private Long codigoProcedimento;
	
	private Float valor;
	
	public AgendamentoNovoDTO() {
	}
	
	public AgendamentoNovoDTO(Agendamento agendamento) {
		this.dataAgendamento = agendamento.getDataAgendamento();
		this.horaInicio = converterHoraParaString(agendamento.getHoraInicio());
		this.horaFim = converterHoraParaString(agendamento.getHoraFim());
		this.statusAgendamento = agendamento.getStatusAgendamento().getCodigo();
		this.observacao = agendamento.getObservacao();
		this.primeiraAvalicao = agendamento.getPrimeiraAvalicao();
		this.codigoOrcamento = agendamento.getOrcamento().getCodigo();
		this.codigoProcedimento = agendamento.getProcedimento().getCodigo();
		this.valor = agendamento.getValor();
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

	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}
	
	

	public Long getCodigoOrcamento() {
		return codigoOrcamento;
	}

	public void setCodigoOrcamento(Long codigoOrcamento) {
		this.codigoOrcamento = codigoOrcamento;
	}
	
	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	private String converterHoraParaString(Date hora) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
        return dateFormat.format(hora);  
	}
	
	
}
