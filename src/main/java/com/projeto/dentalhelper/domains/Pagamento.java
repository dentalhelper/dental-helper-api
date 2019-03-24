package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pagamento")
public class Pagamento extends ObjetoIdentificado {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Date dataPagamento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private FormaDePagamento forma;

	private Float valor;

	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public FormaDePagamento getForma() {
		return forma;
	}

	public Float getValor() {
		return valor;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setForma(FormaDePagamento formaDePagamento) {
		this.forma = formaDePagamento;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

}
