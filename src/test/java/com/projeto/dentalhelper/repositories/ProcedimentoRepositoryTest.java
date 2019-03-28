package com.projeto.dentalhelper.repositories;

import static com.projeto.dentalhelper.builders.CategoriaDespesaBuilder.umaCategoriaDespesa;
import static org.assertj.core.api.Assertions.assertThat;

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

import com.projeto.dentalhelper.builders.ProcedimentoBuilder;
import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.services.ProcedimentoService;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = { ProcedimentoService.class })
public class ProcedimentoRepositoryTest {
	@Autowired
	private ProcedimentoService service;
	

	@Rule
	public ExpectedException exceptedException = ExpectedException.none();
	
	public Procedimento criarProcedimentoValido(String nome) {
		Procedimento procedimento = ProcedimentoBuilder.novoProcedimento();
		procedimento.setNome(nome);
		procedimento.setDescricao("Clareamento Dental");
		procedimento.setDuracaoMinutos(new Integer(35));
		procedimento.setValorBase(new Float(50));

		
		return procedimento;
	}
	
	@Test
	public void deveSalvarUmProcedimento() throws Exception {
		Procedimento procedimento = criarProcedimentoValido("Clareamento");
		procedimento = this.service.salvar(procedimento);

		assertThat(procedimento.getCodigo()).isNotNull();
		assertThat(procedimento.getNome()).isEqualTo("Clareamento");
	}
	
	@Test
	public void deveSalvarUmProcedimentoSemDescricao() throws Exception {
		Procedimento procedimento = criarProcedimentoValido("Clareamento1");
		procedimento.setDescricao(null);
		procedimento = this.service.salvar(procedimento);
		

		assertThat(procedimento.getCodigo()).isNotNull();
		assertThat(procedimento.getDescricao()).isEqualTo(procedimento.getNome());
	}
	
	
	

	@Test
	public void deveDeletarUmProcedimento() throws Exception {

		Procedimento procedimento = criarProcedimentoValido("Clareamento2");
		procedimento = this.service.salvar(procedimento);

		this.service.deletar(procedimento.getCodigo());

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage("Objeto não encontrado! Id: " + procedimento.getCodigo() + ", Tipo: "
				+ ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(procedimento.getCodigo());
	}

	@Test
	public void deveAtualizarUmProcedimento() throws Exception {
		Procedimento procedimento = criarProcedimentoValido("Arcada dentária");
		
		procedimento = this.service.salvar(procedimento);
		
		Procedimento procedimentoEdicao = criarProcedimentoValido("Arcada d");
		procedimentoEdicao.setDescricao("Arcada");
		
		procedimento = this.service.atualizar(procedimento.getCodigo(), procedimentoEdicao);


		assertThat(procedimento.getDescricao()).isEqualTo("Arcada");
	}


	@Test
	public void naoDeveSalvarProcedimentoComValorBaseVazio() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);
		
		Procedimento procedimento = criarProcedimentoValido("Arcada dentária");
		
		procedimento.setValorBase(null);

		procedimento = this.service.salvar(procedimento);
	}
	
	@Test
	public void naoDeveSalvarProcedimentoComDuracaoVazio() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);
		
		Procedimento procedimento = criarProcedimentoValido("Arcada dentária");
		
		procedimento.setDuracaoMinutos(null);

		procedimento = this.service.salvar(procedimento);
	}
	
	@Test
	public void naoDeveSalvarProcedimentoSemNome() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);
		
		Procedimento procedimento = criarProcedimentoValido("Arcada dentária");
		
		procedimento.setNome(null);

		procedimento = this.service.salvar(procedimento);
	}
	
	@Test
	public void naoDeveSalvarProcedimentoComMaisDe50CaracteresNoNome() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);
		
		Procedimento procedimento = criarProcedimentoValido("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		

		procedimento = this.service.salvar(procedimento);
	}
	
	@Test
	public void naoDeveSalvarProcedimentoComMaisDe50CaracteresNaDescricao() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);
		
		Procedimento procedimento = criarProcedimentoValido("Arcada");
		procedimento.setDescricao("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		procedimento = this.service.salvar(procedimento);
	}

	@Test
	public void deveBuscarUmProcedimentoPeloId() throws Exception {
		Procedimento procedimento = criarProcedimentoValido("Arcada");
		procedimento = this.service.salvar(procedimento);

		Procedimento procedimentoBuscado = this.service.buscarPorCodigo(procedimento.getCodigo());

		assertThat(this.service.buscarPorCodigo(procedimento.getCodigo())).isNotNull();
		assertThat(this.service.buscarPorCodigo(procedimento.getCodigo()).getDescricao())
				.isEqualTo(procedimentoBuscado.getDescricao());
	}

	@Test
	public void naoDeveBuscarProcedimentoComIdInexistente() throws Exception {
		long idInexistente = 50;
		Procedimento procedimento = criarProcedimentoValido("Arcada");
		procedimento = this.service.salvar(procedimento);

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage(
				"Objeto não encontrado! Id: " + idInexistente + ", Tipo: " + ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(idInexistente);
	}

}
