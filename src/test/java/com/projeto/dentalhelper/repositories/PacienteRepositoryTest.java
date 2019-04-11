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

import com.projeto.dentalhelper.builders.PacienteBuilder;
import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.ObjetoIdentificado;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.services.PacienteService;
import com.projeto.dentalhelper.services.exceptions.ObjetoNaoEncontradoException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoException;


@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = { PacienteService.class })
public class PacienteRepositoryTest {
	
	@Autowired
	private PacienteService service;
	
	@Rule
	public ExpectedException exceptedException = ExpectedException.none();
	
	
	
	public Paciente criarPacienteValido() {
		Paciente paciente = PacienteBuilder.novoPaciente();
		paciente.setNome("Aroldo Alves");
		paciente.setDataNascimento(new Date());
		paciente.setcPF("154.698.453-19");
		paciente.setrG("7809487");
		paciente.setProfissao("Marceneiro");
		paciente.setFotoPerfil("001010001100");
		paciente.setDataCriacaoFicha(new Date());
		paciente.setEmail("aroldo_@gmail.com");
		paciente.setEstadoCivil(EstadoCivil.SOLTEIRO);
		
		return paciente;
	}

	@Test
	public void deveSalvarUmPaciente() throws Exception {
		Paciente paciente = criarPacienteValido();
		paciente = this.service.salvar(paciente);

		assertThat(paciente.getCodigo()).isNotNull();
		assertThat(paciente.getNome()).isEqualTo("Aroldo Alves");
	}
	
	@Test
	public void deveDeletarUmPaciente() throws Exception {

		Paciente paciente = criarPacienteValido();
		
		paciente = this.service.salvar(paciente);

		this.service.deletar(paciente.getCodigo());

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage("Objeto não encontrado! Id: " + paciente.getCodigo() + ", Tipo: "
				+ ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(paciente.getCodigo());
	}
	
	@Test
	public void deveAtualizarUmPaciente() throws Exception {
		Paciente paciente = criarPacienteValido();
		paciente = this.service.salvar(paciente);
		
		Paciente nomeEdicao = paciente;
		paciente.setNome("Antenor alves");

		paciente = this.service.atualizar(paciente.getCodigo(), nomeEdicao);

		assertThat(paciente.getNome()).isEqualTo("Antenor alves");
	}
	
	@Test
	public void naoDeveSalvarPacienteComNomeVazio() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Paciente paciente = criarPacienteValido();
		paciente.setNome(null);

		paciente = this.service.salvar(paciente);
	}
	
	@Test
	public void naoDeveSalvarPacienteComCpfVazio() throws Exception {
		exceptedException.expect(RecursoCpfDuplicadoException.class);

		Paciente paciente = criarPacienteValido();
		paciente.setcPF(null);

		paciente = this.service.salvar(paciente);
	}
	
	@Test
	public void naoDeveSalvarPacienteComRgVazio() throws Exception {
		exceptedException.expect(RecursoRgDuplicadoException.class);

		Paciente paciente = criarPacienteValido();
		paciente.setrG(null);

		paciente = this.service.salvar(paciente);
	}
	
	@Test
	public void naoDeveSalvarPacienteComMaisDe50CaracteresNoEmail() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Paciente paciente = criarPacienteValido();
		paciente.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		paciente = this.service.salvar(paciente);
	}
	
	@Test
	public void naoDeveSalvarPacienteComMaisDe50CaracteresNoRg() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Paciente paciente = criarPacienteValido();
		paciente.setrG("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		paciente = this.service.salvar(paciente);
	}
	@Test
	public void naoDeveSalvarPacienteComMaisDe50CaracteresNoCpf() throws Exception {
		exceptedException.expect(ConstraintViolationException.class);

		Paciente paciente = criarPacienteValido();
		paciente.setcPF("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		paciente = this.service.salvar(paciente);
	}
	
	@Test
	public void deveBuscarUmPacientePeloId() throws Exception {
		Paciente paciente = criarPacienteValido();
		paciente = this.service.salvar(paciente);

		Paciente pacienteBuscada = this.service.buscarPorCodigo(paciente.getCodigo());

		assertThat(this.service.buscarPorCodigo(paciente.getCodigo())).isNotNull();
		assertThat(this.service.buscarPorCodigo(paciente.getCodigo()).getNome())
				.isEqualTo(pacienteBuscada.getNome());
	}
	
	@Test
	public void naoDeveBuscarPacienteComIdInexistente() throws Exception {
		long idInexistente = 50;
		Paciente paciente = criarPacienteValido();
		paciente = this.service.salvar(paciente);

		exceptedException.expect(ObjetoNaoEncontradoException.class);
		exceptedException.expectMessage(
				"Objeto não encontrado! Id: " + idInexistente + ", Tipo: " + ObjetoIdentificado.class.getName());

		this.service.buscarPorCodigo(idInexistente);
	}

}
