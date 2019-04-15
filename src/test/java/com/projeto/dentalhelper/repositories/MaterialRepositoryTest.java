package com.projeto.dentalhelper.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.projeto.dentalhelper.builders.MaterialBuilder;
import com.projeto.dentalhelper.domains.AtributoMaterial;
import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.services.MaterialService;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;

@ComponentScan(basePackageClasses = { MaterialService.class })
public class MaterialRepositoryTest extends AbstractRepositoryConfig {
	
	@Autowired
	private MaterialService service;
	
	public Material criarMaterialValido() {
		Material material = MaterialBuilder.novoMaterial();
		material.setNome("Pasta");
		material.setFabricante("Colgate");
		
		List<AtributoMaterial> atributos = new ArrayList<AtributoMaterial>();
		AtributoMaterial atributo = new AtributoMaterial();
		atributo.setNome("miligramas");
		atributo.setValor("30mg");
		atributos.add(atributo);
		
		material.setAtributoMateriais(atributos);
		
		return material;
	}

	@Test
	public void deveSalvarUmMaterial() throws Exception {
		Material material = criarMaterialValido();
		material = this.service.salvar(material);

		assertThat(material.getCodigo()).isNotNull();
		assertThat(material.getNome()).isEqualTo("Pasta");
	}

	@Test
	public void deveSalvarUmMaterialSemAtributo() throws Exception {
		Material material = criarMaterialValido();
		material.getAtributoMateriais().clear();
		material = this.service.salvar(material);

		assertThat(material.getCodigo()).isNotNull();
		assertThat(material.getNome()).isEqualTo("Pasta");
	}
	
	

	@Test
	public void deveDeletarUmMaterial() throws Exception {

		Material material = criarMaterialValido();
		
		material = this.service.salvar(material);

		this.service.deletar(material.getCodigo());

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage("Objeto não encontrado! Id: " + material.getCodigo() + ", Tipo: "
				+ ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(material.getCodigo());
	}

	@Test
	public void deveAtualizarUmMaterial() throws Exception {
		Material material = criarMaterialValido();
		
		material = this.service.salvar(material);
		
		Material materialEdicao = material;
		materialEdicao.setNome("Super pasta");
		materialEdicao.getAtributoMateriais().get(0).setNome("quantidade");
		

		material = this.service.atualizar(material.getCodigo(), materialEdicao);
		

		assertThat(material.getNome()).isEqualTo("Super pasta");
	}
	
	
	@Test
	public void naoDeveSalvarMaterialComNomeVazio() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Material material = criarMaterialValido();
		material.setNome(null);

		material = this.service.salvar(material);
	}
	
	@Test
	public void deveSalvarMaterialComFabricanteVazio() throws Exception {

		Material material = criarMaterialValido();
		material.setFabricante(null);

		material = this.service.salvar(material);
		
		assertThat(material.getCodigo()).isNotNull();
		assertThat(material.getNome()).isEqualTo("Pasta");
	}
	
	@Test
	public void naoDeveSalvarMaterialComAtributoSemNome() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Material material = criarMaterialValido();
		material.getAtributoMateriais().get(0).setNome(null);

		material = this.service.salvar(material);
	}
	
	@Test
	public void naoDeveSalvarMaterialComAtributoSemValor() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Material material = criarMaterialValido();
		material.getAtributoMateriais().get(0).setValor(null);

		material = this.service.salvar(material);
	}
	
	@Test
	public void deveBuscarUmMaterialPeloId() throws Exception {
		Material material = criarMaterialValido();
		material = this.service.salvar(material);
		
		Material despesaBuscada = this.service.buscarPorCodigo(material.getCodigo());

		assertThat(this.service.buscarPorCodigo(material.getCodigo())).isNotNull();
		assertThat(this.service.buscarPorCodigo(material.getCodigo()).getNome()).isEqualTo(despesaBuscada.getNome());
	}

	@Test
	public void naoDeveBuscarMaterialComIdInexistente() throws Exception {
		long idInexistente = 50;
		Material material = criarMaterialValido();
		material = this.service.salvar(material);

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage(
				"Objeto não encontrado! Id: " + idInexistente + ", Tipo: " + ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(idInexistente);
	}

}
