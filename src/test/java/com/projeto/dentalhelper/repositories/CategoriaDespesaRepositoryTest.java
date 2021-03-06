package com.projeto.dentalhelper.repositories;

import static com.projeto.dentalhelper.builders.CategoriaDespesaBuilder.umaCategoriaDespesa;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.services.CategoriaDespesaService;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;

@ComponentScan(basePackageClasses = { CategoriaDespesaService.class })
public class CategoriaDespesaRepositoryTest extends AbstractRepositoryConfig {

	@Autowired
	private CategoriaDespesaService service;

	@Test
	public void deveSalvarUmaCategoria() throws Exception {
		CategoriaDespesa categoria = umaCategoriaDespesa().agora();

		categoria = this.service.salvar(categoria);

		assertThat(categoria.getCodigo()).isNotNull();
		assertThat(categoria.getNome()).isEqualTo("Combustível");
	}

	@Test
	public void deveDeletarUmaCategoria() throws Exception {
		CategoriaDespesa categoria = umaCategoriaDespesa().agora();
		categoria = this.service.salvar(categoria);

		this.service.deletar(categoria.getCodigo());

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage("Objeto não encontrado! Id: " + categoria.getCodigo() + ", Tipo: "
				+ ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(categoria.getCodigo());
	}

	@Test
	public void deveAtualizarUmaCategoria() throws Exception {
		CategoriaDespesa categoria = umaCategoriaDespesa().agora();
		categoria = this.service.salvar(categoria);
		CategoriaDespesa categoriaEdicao = umaCategoriaDespesa().agora();
		categoriaEdicao.setNome("Água");

		categoria = this.service.atualizar(categoria.getCodigo(), categoriaEdicao);

		assertThat(categoria.getNome()).isEqualTo("Água");
	}

	@Test
	public void deveBuscarUmaCategoriaPeloNome() throws Exception {
		CategoriaDespesa categoria = umaCategoriaDespesa().agora();
		categoria = this.service.salvar(categoria);

		List<CategoriaDespesa> categoriaBuscada = this.service.buscarPeloNome("Combustível");

		assertThat(categoriaBuscada.size()).isEqualTo(1);
	}

	@Test
	public void naoDeveSalvarCategoriaComNomeVazio() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		CategoriaDespesa categoria = umaCategoriaDespesa().semNome().agora();

		categoria = this.service.salvar(categoria);
	}

	@Test
	public void naoDeveSalvarCategoriaComMenosDe3CarecteresNoNome() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		CategoriaDespesa categoria = umaCategoriaDespesa().comNome("AA").agora();

		categoria = this.service.salvar(categoria);
	}

	@Test
	public void naoDeveSalvarCategoriaComMaisDe30CarecteresNoNome() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		CategoriaDespesa categoria = umaCategoriaDespesa().comNome("123456789-123456789-123456789-*").agora();

		categoria = this.service.salvar(categoria);
	}

	@Test
	public void deveBuscarUmaCategoriaPeloId() throws Exception {
		CategoriaDespesa categoria = umaCategoriaDespesa().agora();
		categoria = this.service.salvar(categoria);

		this.service.buscarPorCodigo(categoria.getCodigo());

		assertThat(this.service.buscarPorCodigo(categoria.getCodigo())).isNotNull();
		assertThat(this.service.buscarPorCodigo(categoria.getCodigo()).getNome()).isEqualTo(categoria.getNome());
	}

	@Test
	public void naoDeveBuscarCategoriaComIdInexistente() throws Exception {
		long idInexistente = 50;
		CategoriaDespesa categoria = umaCategoriaDespesa().agora();
		categoria = this.service.salvar(categoria);

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage(
				"Objeto não encontrado! Id: " + idInexistente + ", Tipo: " + ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(idInexistente);
	}
}
