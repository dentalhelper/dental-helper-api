package com.projeto.dentalhelper.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "material")
public class Material extends ObjetoIdentificado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotNull
	@Size( max = 30)
	private String nome;
	@Size( max = 50)
	private String fabricante;	
	
	@OneToMany
	private List<AtributoMaterial> atributoMaterial;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public List<AtributoMaterial> getAtributoMaterial() {
		return atributoMaterial;
	}
	public void setAtributoMaterial(List<AtributoMaterial> atributoMaterial) {
		this.atributoMaterial = atributoMaterial;
	}
	
	

}
