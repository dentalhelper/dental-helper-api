package com.projeto.dentalhelper.domains;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "atributo_material")
public class AtributoMaterial extends ObjetoIdentificado{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String nome;
	
	@NotBlank
	@Size(min = 3, max = 30)
	private String valor;
		
	@ManyToOne
	@JoinColumn(name = "codigo_material")
	private Material material;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "AtributoMaterial [nome=" + nome + ", valor=" + valor + ", material=" + material + "]";
	}
	
}
