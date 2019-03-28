package com.projeto.dentalhelper.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "material")
public class Material extends ObjetoIdentificado {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	private String nome;

	@Size(max = 50)
	private String fabricante;

	@JsonIgnoreProperties("material")
	@Valid
	@OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AtributoMaterial> atributoMateriais = new ArrayList<AtributoMaterial>();

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

	public List<AtributoMaterial> getAtributoMateriais() {
		return atributoMateriais;
	}

	public void setAtributoMateriais(List<AtributoMaterial> atributoMateriais) {
		this.atributoMateriais = atributoMateriais;
	}

}
