package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pagamento")
public class Pagamento extends ObjetoIdentificado{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Date data;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private FormaDePagamento forma;
	
	private Float valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;

	public Date getData() {
		return data;
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

	public void setData(Date data) {
		this.data = data;
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
