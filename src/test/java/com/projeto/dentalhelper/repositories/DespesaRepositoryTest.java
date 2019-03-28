package com.projeto.dentalhelper.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.projeto.dentalhelper.builders.DespesaBuilder;
import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.enums.FormaDePagamento;
import com.projeto.dentalhelper.services.CategoriaDespesaService;
import com.projeto.dentalhelper.services.DespesaService;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = { DespesaService.class })
public class DespesaRepositoryTest {

	@Autowired
	private DespesaService service;
	
	@Autowired
	private CategoriaDespesaService categoriaService;

	@Rule
	public ExpectedException exceptedException = ExpectedException.none();
	
	
	
	public Despesa criarDespesaValida() {
		Despesa despesa = DespesaBuilder.novaDespesa();
		despesa.setDescricao("Mensalidade Internet");
		despesa.setValor(new Float(35));
		CategoriaDespesa categoria = categoriaService.buscarPorCodigo(new Long(1));
		despesa.setCategoria(categoria);
		Pagamento pagamento = new Pagamento();
		pagamento.setDataPagamento(new Date());
		pagamento.setForma(FormaDePagamento.CARTAO);
		despesa.setPagamento(pagamento);
		
		return despesa;
	}

	@Test
	public void deveSalvarUmaDespesa() throws Exception {
		Despesa despesa = criarDespesaValida();
		despesa = this.service.salvar(despesa);

		assertThat(despesa.getCodigo()).isNotNull();
		assertThat(despesa.getDescricao()).isEqualTo("Mensalidade Internet");
	}
	
	@Test
	public void deveSalvarUmaDespesaSemDescricao() throws Exception {
		Despesa despesa = criarDespesaValida();
		despesa.setDescricao(null);
		despesa = this.service.salvar(despesa);
		

		assertThat(despesa.getCodigo()).isNotNull();
		assertThat(despesa.getDescricao()).isEqualTo(despesa.getCategoria().getNome());
	}
	
	

	@Test
	public void deveDeletarUmaDespesa() throws Exception {

		Despesa despesa = criarDespesaValida();
		
		despesa = this.service.salvar(despesa);

		this.service.deletar(despesa.getCodigo());

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage("Objeto não encontrado! Id: " + despesa.getCodigo() + ", Tipo: "
				+ ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(despesa.getCodigo());
	}

	@Test
	public void deveAtualizarUmaDespesa() throws Exception {
		Despesa categoria = criarDespesaValida();
		categoria = this.service.salvar(categoria);
		
		Despesa categoriaEdicao = categoria;
		categoria.setDescricao("Mensalidade 1");

		categoria = this.service.atualizar(categoria.getCodigo(), categoriaEdicao);

		assertThat(categoria.getDescricao()).isEqualTo("Mensalidade 1");
	}


	@Test
	public void naoDeveSalvarDespesaComValorVazio() throws Exception {
		exceptedException.expect(DataIntegrityViolationException.class);

		Despesa despesa = criarDespesaValida();
		despesa.setValor(null);

		despesa = this.service.salvar(despesa);
	}
	
	@Test
	public void naoDeveSalvarDespesaSemCategoria() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Despesa despesa = criarDespesaValida();
		despesa.setCategoria(null);

		despesa = this.service.salvar(despesa);
	}
	
	@Test
	public void naoDeveSalvarDespesaSemPagamento() throws Exception {
		exceptedException.expect(NullPointerException.class);

		Despesa despesa = criarDespesaValida();
		despesa.setPagamento(null);

		despesa = this.service.salvar(despesa);
	}
	
	@Test
	public void naoDeveSalvarDespesaSemDataDePagamento() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Despesa despesa = criarDespesaValida();
		despesa.getPagamento().setDataPagamento(null);

		despesa = this.service.salvar(despesa);
	}
	
	@Test
	public void naoDeveSalvarDespesaSemFormaDePagamento() throws Exception {
		exceptedException.expect(NullPointerException.class);

		Despesa despesa = criarDespesaValida();
		despesa.getPagamento().setForma(null);

		despesa = this.service.salvar(despesa);
	}
	
	@Test
	public void naoDeveSalvarDespesaComMaisDe50CaracteresNaDescricao() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Despesa despesa = criarDespesaValida();
		despesa.setDescricao("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		despesa = this.service.salvar(despesa);
	}

	@Test
	public void deveBuscarUmaDespesaPeloId() throws Exception {
		Despesa despesa = criarDespesaValida();
		despesa = this.service.salvar(despesa);

		Despesa despesaBuscada = this.service.buscarPorCodigo(despesa.getCodigo());

		assertThat(this.service.buscarPorCodigo(despesa.getCodigo())).isNotNull();
		assertThat(this.service.buscarPorCodigo(despesa.getCodigo()).getDescricao())
				.isEqualTo(despesaBuscada.getDescricao());
	}

	@Test
	public void naoDeveBuscarDespesaComIdInexistente() throws Exception {
		long idInexistente = 50;
		Despesa despesa = criarDespesaValida();
		despesa = this.service.salvar(despesa);

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage(
				"Objeto não encontrado! Id: " + idInexistente + ", Tipo: " + ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(idInexistente);
	}
}
