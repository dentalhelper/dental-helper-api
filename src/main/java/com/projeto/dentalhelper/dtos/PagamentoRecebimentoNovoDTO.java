package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.Pagamento;

public class PagamentoRecebimentoNovoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Date dataPagamento;

	@NotNull
	private Integer forma;
	
	@NotNull
	private Float valor;
	
	@NotNull
	private Long codOrcamento;
	
	public PagamentoRecebimentoNovoDTO() {
	}
	
	public PagamentoRecebimentoNovoDTO(Date dataPagamento, Integer forma, Float valor, Long codOrcamento) {
		this.dataPagamento = dataPagamento;
		this.forma = forma;
		this.valor = valor;
		this.codOrcamento = codOrcamento;
	}
	
	public PagamentoRecebimentoNovoDTO(Pagamento p) {
		this.dataPagamento = p.getDataPagamento();
		this.forma = p.getForma().getCodigo();
		this.valor = p.getValor();
		this.codOrcamento = p.getOrcamento().getCodigo();
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getForma() {
		return forma;
	}

	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Long getCodOrcamento() {
		return codOrcamento;
	}

	public void setCodOrcamento(Long codOrcamento) {
		this.codOrcamento = codOrcamento;
	}
	
	
	
}
