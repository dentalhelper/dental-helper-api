package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dente")
public class Dente extends ObjetoIdentificado {

	private static final long serialVersionUID = 1L;
	
	private Integer numero;
	private String observacao;
	private Boolean existente;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "codigo_paciente")
	private Paciente paciente;
	
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="dente_procedimento_previsto", joinColumns=
//    {@JoinColumn(name="codigo_dente")}, inverseJoinColumns=
//      {@JoinColumn(name="codigo_procedimento_previsto")})
	
    @JsonIgnoreProperties({"dentes", "orcamento"})
	@ManyToMany(mappedBy="dentes")
	private List<ProcedimentoPrevisto> procedimentosPrevistos = new ArrayList<ProcedimentoPrevisto>();
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Boolean getExistente() {
		return existente;
	}
	public void setExistente(Boolean existente) {
		this.existente = existente;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<ProcedimentoPrevisto> getProcedimentosPrevistos() {
		return procedimentosPrevistos;
	}
	public void setProcedimentosPrevistos(List<ProcedimentoPrevisto> procedimentosPrevistos) {
		this.procedimentosPrevistos = procedimentosPrevistos;
	}
	

}
