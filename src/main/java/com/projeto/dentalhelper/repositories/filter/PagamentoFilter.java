package com.projeto.dentalhelper.repositories.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PagamentoFilter {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPagamento;
	
	private Integer tipo;

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
}
