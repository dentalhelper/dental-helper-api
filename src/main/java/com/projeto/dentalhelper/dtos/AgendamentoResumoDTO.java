package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Agendamento;

public class AgendamentoResumoDTO extends ResourceSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long codigoAgendamento;
	
	private Date dataAgendamento;
	
	private Date horaInicio;
	
	private Date horaFim;
	
	private Boolean primeiraAvalicao;
	
	private Long codigoPaciente;
	
	private String nomePaciente;
	
	private Long codigoProcedimento;
	
	private String nomeProcedimento;
	
	private Integer statusAgendamento;
	
	private String observacao;
	
	public AgendamentoResumoDTO(Agendamento a) {
		this.codigoAgendamento = a.getCodigo();
		this.dataAgendamento = a.getDataAgendamento();
		this.horaInicio = a.getHoraInicio();
		this.horaFim = a.getHoraFim();
		this.primeiraAvalicao = a.getPrimeiraAvalicao();
		this.codigoPaciente = a.getOrcamento().getPaciente().getCodigo();
		this.codigoProcedimento = a.getProcedimento().getCodigo();
		this.statusAgendamento = a.getStatusAgendamento().getCodigo();
		this.observacao = a.getObservacao();
		this.nomePaciente = a.getOrcamento().getPaciente().getNome();
		this.nomeProcedimento = a.getProcedimento().getNome();
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

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
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

	public Long getCodigoProcedimento() {
		return codigoProcedimento;
	}

	public void setCodigoProcedimento(Long codigoProcedimento) {
		this.codigoProcedimento = codigoProcedimento;
	}

	public Integer getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(Integer statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}
	
	
	
	
	
	
	
}
