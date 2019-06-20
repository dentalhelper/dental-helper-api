package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "procedimento_previsto")
public class ProcedimentoPrevisto extends ObjetoIdentificado{

	private static final long serialVersionUID = 1L;

	@Column(name = "valor_procedimento")
	private Float valorDoProcedimento;
	
	private Boolean finalizado;
	
	@Column(name = "data_inicio")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name = "data_finalizacao")
	@Temporal(TemporalType.DATE)
	private Date dataFinalizacao;
	

	@ManyToOne
	@JoinColumn(name = "codigo_procedimento")
	private Procedimento procedimento;
	
	
	@ManyToOne
	@JoinColumn(name = "codigo_orcamento")
	private Orcamento orcamento;
	
	@JsonIgnoreProperties("procedimentosPrevistos")
  @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
  @JoinTable(name="dente_procedimento_previsto", joinColumns=
  {@JoinColumn(name="codigo_procedimento_previsto")}, inverseJoinColumns=
    {@JoinColumn(name="codigo_dente")})
	private List<Dente> dentes = new ArrayList<Dente>();
	
	
	public ProcedimentoPrevisto() {
		super();
	}
	
	public ProcedimentoPrevisto(Float valorDoProcedimento, Boolean finalizado, Date dataInicio,
			 Date dataFinalizacao, Procedimento procedimento, Orcamento orcamento, List<Dente> dentes) {
		super();
		this.valorDoProcedimento = valorDoProcedimento;
		this.finalizado = finalizado;
		this.dataInicio = dataInicio;
		this.dataFinalizacao = dataFinalizacao;
		this.procedimento = procedimento;
		this.orcamento = orcamento;
		this.dentes = dentes;
	}




	public Float getValorDoProcedimento() {
		return valorDoProcedimento;
	}

	public void setValorDoProcedimento(Float valorDoProcedimento) {
		this.valorDoProcedimento = valorDoProcedimento;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public List<Dente> getDentes() {
		return dentes;
	}

	public void setDentes(List<Dente> dentes) {
		this.dentes = dentes;
	}
	
	
	
	
}
