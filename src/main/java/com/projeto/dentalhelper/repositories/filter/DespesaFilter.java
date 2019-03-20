package com.projeto.dentalhelper.repositories.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class DespesaFilter {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPrevistaDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPrevistaAte;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataRealizadaDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataRealizadaAte;

	private String descricao;
	
	private String categoria;
	
	private Boolean paga;

	public Date getDataPrevistaDe() {
		return dataPrevistaDe;
	}

	public Date getDataPrevistaAte() {
		return dataPrevistaAte;
	}

	public Date getDataRealizadaDe() {
		return dataRealizadaDe;
	}

	public Date getDataRealizadaAte() {
		return dataRealizadaAte;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setDataPrevistaDe(Date dataPrevistaDe) {
		this.dataPrevistaDe = dataPrevistaDe;
	}

	public void setDataPrevistaAte(Date dataPrevistaAte) {
		this.dataPrevistaAte = dataPrevistaAte;
	}

	public void setDataRealizadaDe(Date dataRealizadaDe) {
		this.dataRealizadaDe = dataRealizadaDe;
	}

	public void setDataRealizadaAte(Date dataRealizadaAte) {
		this.dataRealizadaAte = dataRealizadaAte;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Boolean isPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}
	
	
	
}
