package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "anamnese")
public class Anamnese extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Temporal(TemporalType.DATE)
	@Column(name = "data_resp")
	private Date dataResp;
	
	@JsonIgnoreProperties("anamnese")
	@Valid
	@OneToMany(mappedBy = "anamnese", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
	private List<Questao> questoes = new ArrayList<Questao>();

	public Date getDataResp() {
		return dataResp;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setDataResp(Date dataResp) {
		this.dataResp = dataResp;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	
	

}
