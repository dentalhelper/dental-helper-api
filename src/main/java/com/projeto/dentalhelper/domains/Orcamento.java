package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
//	@ManyToMany
//    @JoinTable(name="orcamento_procedimento", joinColumns=
//    {@JoinColumn(name="codigo_orcamento")}, inverseJoinColumns=
//    {@JoinColumn(name="codigo_procedimento")})
//	private List<Procedimento> procedimentos = new ArrayList<Procedimento>();
	
	
	@JsonIgnoreProperties("orcamento")
	@Valid
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProcedimentoPrevisto> procedimentosPrevistos = new ArrayList<ProcedimentoPrevisto>();
	
	@ManyToOne
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	

	@JsonIgnoreProperties("orcamento")
	@Valid
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();
	
	
	public Orcamento () {
		
	}

	public Orcamento(Float valorTotal, Date dataOrcamento, Boolean aprovado, List<ProcedimentoPrevisto> procedimentosPrevistos,
			Paciente paciente, List<Pagamento> pagamentos) {
		super();
		this.valorTotal = valorTotal;
		this.dataOrcamento = dataOrcamento;
		this.aprovado = aprovado;
		this.procedimentosPrevistos = procedimentosPrevistos;
		this.paciente = paciente;
		this.pagamentos = pagamentos;
	}
	
	public Orcamento(Float valorTotal, Boolean aprovado, List<ProcedimentoPrevisto> procedimentosPrevistos,
			Paciente paciente, List<Pagamento> pagamentos) {
		super();
		this.valorTotal = valorTotal;
		this.aprovado = aprovado;
		this.procedimentosPrevistos = procedimentosPrevistos;
		this.paciente = paciente;
		this.pagamentos = pagamentos;
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public List<ProcedimentoPrevisto> getProcedimentosPrevistos() {
		return procedimentosPrevistos;
	}

	public void setProcedimentosPrevistos(List<ProcedimentoPrevisto> procedimentosPrevistos) {
		this.procedimentosPrevistos = procedimentosPrevistos;
	}
	
	

}
