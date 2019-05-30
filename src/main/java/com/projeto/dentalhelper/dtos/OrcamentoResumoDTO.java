package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Orcamento;

public class OrcamentoResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigoOrcamento;

	private Float valorTotal;

	private Date dataOrcamento;

	private Boolean aprovado;

	public OrcamentoResumoDTO(Orcamento orcamento) {
		this.codigoOrcamento = orcamento.getCodigo();
		this.valorTotal = orcamento.getValorTotal();
		this.dataOrcamento = orcamento.getDataOrcamento();
		this.aprovado = orcamento.getAprovado();
		this.getLinks().addAll(orcamento.getLinks());
	}

	public Long getCodigoOrcamento() {
		return codigoOrcamento;
	}

	public void setCodigoOrcamento(Long codigoOrcamento) {
		this.codigoOrcamento = codigoOrcamento;
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

}
