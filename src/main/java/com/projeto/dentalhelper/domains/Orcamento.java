package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orcamento")
public class Orcamento extends ObjetoIdentificado{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "valor_total")
	private Float valorTotal;
	
	@Column(name = "data_orcamento")
	@Temporal(TemporalType.DATE)
	private Date dataOrcamento;
	
	private Boolean aprovado;
	
	@ManyToMany
    @JoinTable(name="orcamento_procedimento", joinColumns=
    {@JoinColumn(name="codigo_orcamento")}, inverseJoinColumns=
    {@JoinColumn(name="codigo_procedimento")})
	private List<Procedimento> procedimentos = new ArrayList<Procedimento>();
	
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	
	public Orcamento () {
		
	}

	public Orcamento(Float valorTotal, Date dataOrcamento, Boolean aprovado, List<Procedimento> procedimentos,
			Paciente paciente) {
		super();
		this.valorTotal = valorTotal;
		this.dataOrcamento = dataOrcamento;
		this.aprovado = aprovado;
		this.procedimentos = procedimentos;
		this.paciente = paciente;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataOrcamento() {
		return dataOrcamento;
	}

	public void setDataOrcamento(Date dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	
	
	

}
