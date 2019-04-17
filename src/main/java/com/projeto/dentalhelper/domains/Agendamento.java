package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.enums.StatusAgendamento;

@Entity
@Table(name = "agendamento")
public class Agendamento extends ObjetoIdentificado{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "data_agendamento")
	@Temporal(TemporalType.DATE)
	private Date dataAgendamento;
	
	@NotNull
	@Column(name = "hora_inicio")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@NotNull
	@Column(name = "hora_fim")
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@NotNull
	@Column(name = "status_agendamento")
	private Integer statusAgendamento;
	
	private String observacao;
	
	@NotNull
	private Boolean primeiraAvalicao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_procedimento")
	private Procedimento procedimento;


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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	

	
	
	
	
	
	
	

}
