package com.projeto.dentalhelper.repositories.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class DespesaFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPagamentoDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPagamentoAte;

	private String descricao;
	
	private String categoria;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPagamento;
	
	

	public Date getDataPagamentoDe() {
		return dataPagamentoDe;
	}

	public Date getDataPagamentoAte() {
		return dataPagamentoAte;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setDataPagamentoDe(Date dataPagamentoDe) {
		this.dataPagamentoDe = dataPagamentoDe;
	}

	public void setDataPagamentoAte(Date dataPagamentoAte) {
		this.dataPagamentoAte = dataPagamentoAte;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
	
	
}
