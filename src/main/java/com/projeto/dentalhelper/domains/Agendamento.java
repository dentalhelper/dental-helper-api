package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projeto.dentalhelper.domains.enums.StatusAgendamento;

@Entity
@Table(name = "agendamento")
public class Agendamento extends ObjetoIdentificado{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Column(name = "data_agendamento")
	@Temporal(TemporalType.DATE)
	private Date dataAgendamento;
	

	@Column(name = "hora_inicio")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	

	@Column(name = "hora_fim")
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	

	@Column(name = "status_agendamento")
	private Integer statusAgendamento;
	
	private String observacao;
	

	@Column(name = "primeira_avaliacao")
	private Boolean primeiraAvalicao;
	

	@ManyToOne
	@JoinColumn(name = "codigo_orcamento")
	private Orcamento orcamento;
	

	@ManyToOne
	@JsonIgnoreProperties("orcamento")
	@JoinColumn(name = "codigo_procedimento_previsto")
	private ProcedimentoPrevisto procedimentoPrevisto;
	
	private Float valor;
	
	public Agendamento() {
		
	}
	
	


	public Agendamento(Date dataAgendamento, Date horaInicio, Date horaFim, StatusAgendamento statusAgendamento,
			String observacao, Boolean primeiraAvalicao, Orcamento orcamento, ProcedimentoPrevisto procedimentoPrevisto, Float valor) {
		super();
		this.dataAgendamento = dataAgendamento;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.statusAgendamento = (statusAgendamento==null) ? null : statusAgendamento.getCodigo();
		this.observacao = observacao;
		this.primeiraAvalicao = primeiraAvalicao;
		this.orcamento = orcamento;
		this.procedimentoPrevisto = procedimentoPrevisto;
		this.valor = valor;
	}




	public StatusAgendamento getStatusAgendamento() {
		return StatusAgendamento.toEnum(statusAgendamento);
	}

	public void setStatusAgendamento(StatusAgendamento status) {
		this.statusAgendamento = status.getCodigo();
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


	public ProcedimentoPrevisto getProcedimentoPrevisto() {
		return procedimentoPrevisto;
	}




	public void setProcedimentoPrevisto(ProcedimentoPrevisto procedimentoPrevisto) {
		this.procedimentoPrevisto = procedimentoPrevisto;
	}




	public Orcamento getOrcamento() {
		return orcamento;
	}
	
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

}
