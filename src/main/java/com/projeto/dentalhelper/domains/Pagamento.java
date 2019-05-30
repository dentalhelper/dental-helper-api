package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.projeto.dentalhelper.domains.enums.FormaDePagamento;
import com.projeto.dentalhelper.domains.enums.TipoPagamento;

@Entity
@Table(name = "pagamento")
public class Pagamento extends ObjetoIdentificado {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	@NotNull
	private Integer forma;

	private Float valor;


	private Integer tipo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_orcamento")
	private Orcamento orcamento;
	
	public Pagamento() {}
	

	public Pagamento(Date dataPagamento, Integer forma, Float valor, Integer tipo,
			Orcamento orcamento) {
		super();
		this.dataPagamento = dataPagamento;
		this.forma = forma;
		this.valor = valor;
		this.tipo = tipo;
		this.orcamento = orcamento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}

	public FormaDePagamento getForma() {
		return FormaDePagamento.toEnum(forma);
	}

	public Float getValor() {
		return valor;
	}

	public TipoPagamento getTipo() {
		return TipoPagamento.toEnum(tipo);
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public void setForma(FormaDePagamento formaDePagamento) {
		this.forma = formaDePagamento.getCodigo();
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public void setTipo(TipoPagamento tipoPagamento) {
		this.tipo = tipoPagamento.getCodigo();
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	

}
