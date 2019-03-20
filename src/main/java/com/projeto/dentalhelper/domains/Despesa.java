package com.projeto.dentalhelper.domains;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "despesa")
public class Despesa extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Date dataPrevista;
	private Date dataRealizada;
	@NotNull
	private Float valor;
	@NotNull
	@Size(min = 3, max = 50)
	private String descricao;
	private String comprovante;
	
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private CategoriaDespesa categoria;
	
	public Date getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public Date getDataRealizada() {
		return dataRealizada;
	}
	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getComprovante() {
		return comprovante;
	}
	public void setComprovante(String comprovante) {
		this.comprovante = comprovante;
	}
	public CategoriaDespesa getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}
	
	
	
	

}
