package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.domains.Anamnese;
import com.projeto.dentalhelper.domains.Cidade;
import com.projeto.dentalhelper.domains.Dente;
import com.projeto.dentalhelper.domains.Endereco;
import com.projeto.dentalhelper.domains.Foto;
import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.ProcedimentoPrevisto;
import com.projeto.dentalhelper.domains.Questao;
import com.projeto.dentalhelper.domains.QuestaoPreDefinida;
import com.projeto.dentalhelper.domains.Telefone;
import com.projeto.dentalhelper.domains.Usuario;
import com.projeto.dentalhelper.domains.enums.EstadoCivil;
import com.projeto.dentalhelper.domains.enums.FormaDoRosto;
import com.projeto.dentalhelper.domains.enums.RespostaQuestaoAnamnese;
import com.projeto.dentalhelper.domains.enums.Sexo;
import com.projeto.dentalhelper.dtos.OdontogramaResumoDTO;
import com.projeto.dentalhelper.dtos.PacienteNovoDTO;
import com.projeto.dentalhelper.repositories.AgendamentoRepository;
import com.projeto.dentalhelper.repositories.CidadeRepository;
import com.projeto.dentalhelper.repositories.OrcamentoRepository;
import com.projeto.dentalhelper.repositories.PacienteRepository;
import com.projeto.dentalhelper.repositories.ProcedimentoPrevistoRepository;
import com.projeto.dentalhelper.repositories.QuestaoPreDefinidaRepository;
import com.projeto.dentalhelper.repositories.UsuarioRepository;
import com.projeto.dentalhelper.repositories.filter.OrcamentoFilter;
import com.projeto.dentalhelper.repositories.filter.PacienteFilter;
import com.projeto.dentalhelper.repositories.filter.ProcedimentoPrevistoFilter;
import com.projeto.dentalhelper.repositories.filter.UsuarioFilter;
import com.projeto.dentalhelper.services.exceptions.CpfJaCadastradoException;
import com.projeto.dentalhelper.services.exceptions.IntegridadeDeDadosException;
import com.projeto.dentalhelper.services.exceptions.OdontogramaInvalidoException;
import com.projeto.dentalhelper.services.exceptions.RecursoCpfDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RecursoRgDuplicadoException;
import com.projeto.dentalhelper.services.exceptions.RespostaInvalidaException;
import com.projeto.dentalhelper.services.exceptions.RgJaCadastradoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;
import com.projeto.dentalhelper.services.storage.S3Service;

@Service
public class PacienteService extends AbstractService<Paciente, PacienteRepository>{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private ProcedimentoPrevistoRepository procedimentoPrevistoRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private QuestaoPreDefinidaRepository questoesRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	private static final int PRIMEIRO_ITEM = 0;
	
	@Override
	public Paciente salvar(Paciente objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
				
		CpfJaExiste(objeto, null);
		RgJaExiste(objeto, null);
		
		Calendar calendar = new GregorianCalendar();
		objeto.setDataCriacaoFicha(calendar.getTime());
		
		Anamnese anamnese = new Anamnese();
		
		List<QuestaoPreDefinida> questoesPreDefinidas = questoesRepository.findAll();
		List<Questao> questoes = new ArrayList<Questao>();

		questoesPreDefinidas.forEach(q -> questoes.add(gerarQuestao(q, anamnese)));	
		
		anamnese.setQuestoes(questoes);
		objeto.setAnamnese(anamnese);
		
		objeto.setDentes(criarDentes(objeto));
		
		objeto.setEscalaDente("");
		objeto.setCorDente("");
		objeto.setFormaRosto(FormaDoRosto.REDONDO);
		
		if(StringUtils.hasText(objeto.getFotoPerfil())) {
			s3Service.salvar(objeto.getFotoPerfil());
		}
		return repository.save(objeto);
	}

	private Questao gerarQuestao(QuestaoPreDefinida q, Anamnese anamnese) {
		return new Questao(q.getQuestao(), anamnese);
	}


	@Override
	public Paciente atualizar(Long codigo, Paciente objetoModificado) throws ServiceApplicationException {
		Paciente objetoAtualizado = buscarPorCodigo(codigo);
		



		CpfJaExiste(objetoModificado, codigo);
		RgJaExiste(objetoModificado, codigo);
		
		if(StringUtils.isEmpty(objetoModificado.getFotoPerfil()) 
				&& StringUtils.hasText(objetoAtualizado.getFotoPerfil())) {
			s3Service.remover(objetoAtualizado.getFotoPerfil());
			objetoAtualizado.setUrlDaFoto(null);
			objetoModificado.setUrlDaFoto(null);
		} else if(StringUtils.hasText(objetoModificado.getFotoPerfil()) 
				&& !objetoModificado.getFotoPerfil().equals(objetoAtualizado.getFotoPerfil())) {
			s3Service.substituir(objetoAtualizado.getFotoPerfil(), objetoModificado.getFotoPerfil());
		}
		
		objetoModificado.getEndereco().setCodigo(objetoAtualizado.getEndereco().getCodigo());
		
		if(objetoModificado.getTelefones().size() >=1 && objetoAtualizado.getTelefones().size() >=1) {
			objetoModificado.getTelefones().get(0).setCodigo(objetoAtualizado.getTelefones().get(0).getCodigo());
			
			if(objetoModificado.getTelefones().size() > 1 && objetoAtualizado.getTelefones().size() > 1) {
				objetoModificado.getTelefones().get(1).setCodigo(objetoAtualizado.getTelefones().get(1).getCodigo());
			}
		}
			
		
			
		
		objetoModificado.setDataCriacaoFicha(objetoAtualizado.getDataCriacaoFicha());
		objetoAtualizado.setEndereco(objetoModificado.getEndereco());
		objetoAtualizado.getTelefones().clear();
		objetoAtualizado.getTelefones().addAll(objetoModificado.getTelefones());
		objetoAtualizado.getTelefones().forEach(telefone -> telefone.setPessoa(objetoAtualizado));

		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "telefones", "anamnese");
		return repository.save(objetoAtualizado);
	}
	
	public Paciente atualizarAnamnese(Long codigo, Anamnese anamnese) throws ServiceApplicationException {
		Paciente paciente = buscarPorCodigo(codigo);
		
		if(anamnese.getQuestoes() == null || anamnese.getQuestoes().size() == 0) {
			throw new RespostaInvalidaException("Questões não podem estar em branco");
		}
		
		
		for(Questao q: anamnese.getQuestoes()) {
			if(q.getInformAdicionais() != "") {
				if(q.getResposta() == RespostaQuestaoAnamnese.NAO_RESPONDIDO)
					throw new RespostaInvalidaException("Informações adicionais não podem estar em questões não respondidas.");
			}
		}
		
		
		Calendar calendar = new GregorianCalendar();
		anamnese.setDataResp(calendar.getTime());
		
		List<Questao> questoes = new ArrayList<Questao>();
		
		if(paciente.getAnamnese()!=null) {
			questoes = paciente.getAnamnese().getQuestoes();
		}
		
		if(questoes.size() <= anamnese.getQuestoes().size()) {
			for(int i = 0; i<questoes.size(); i++) {
				anamnese.getQuestoes().get(i).setCodigo(questoes.get(i).getCodigo());
			}
		} else {
			if(anamnese.getQuestoes().size() > 0) {
				if(questoes.size() > 0) {
					for(int i = 0; i<anamnese.getQuestoes().size(); i++) {
						anamnese.getQuestoes().get(i).setCodigo(questoes.get(i).getCodigo());
					}
				}
			}
		}
		
		anamnese.getQuestoes().forEach(questao -> questao.setAnamnese(anamnese));
		
		anamnese.setCodigo(paciente.getAnamnese().getCodigo());
		
		
		paciente.setAnamnese(anamnese);
		
		
		return repository.save(paciente);
	}
	
	
	public List<Paciente> pesquisar(String filtro){
		
		String cpf = filtro.trim();
		char[] c = cpf.toCharArray();
		
		PacienteFilter filter = new PacienteFilter();
		
		if(Character.isDigit(c[0])) {
			filter.setCpf(cpf);
			return repository.buscarPorCpf(filter);
		}
		
		filter.setNome(filtro);
		return repository.filtrar(filter);
		
	}
	
	
	private boolean CpfJaExiste(Paciente objeto, Long codigoDoObjetoAtualizado) throws RecursoCpfDuplicadoException, CpfJaCadastradoException {
		PacienteFilter filter = new PacienteFilter();
		filter.setCpf(objeto.getcPF());
		UsuarioFilter filterUsuario = new UsuarioFilter();
		filterUsuario.setCpf(objeto.getcPF());
		
		List<Paciente> listaDeObjetos = repository.buscarPorCpf(filter);
		List<Usuario> listaDeUsuarios = usuarioRepository.buscarPorCpf(filterUsuario);
		
		if(!listaDeObjetos.isEmpty()){
			Paciente pacienteExistente = obterPacienteExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(pacienteExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoCpfDuplicadoException(Long.toString(pacienteExistente.getCodigo()));
		}else if(!listaDeUsuarios.isEmpty()) {
			throw new CpfJaCadastradoException("Um usuário com o cpf: '"+objeto.getcPF()+"' já existe");
		}
		
		return false;
		
	}
	
	private boolean RgJaExiste(Paciente objeto, Long codigoDoObjetoAtualizado) throws RecursoRgDuplicadoException, RgJaCadastradoException {
		PacienteFilter filter = new PacienteFilter();
		filter.setRg(objeto.getrG());
		
		List<Paciente> listaDeObjetos = repository.buscarPorRg(filter);
		
		
		UsuarioFilter filterUsuario = new UsuarioFilter();
		filterUsuario.setRg(objeto.getrG());
		
		List<Usuario> listaDeUsuarios = usuarioRepository.buscarPorRg(filterUsuario);
		
		if(!listaDeObjetos.isEmpty()){
			Paciente pacienteExistente = obterPacienteExistente(listaDeObjetos);
			if(codigoDoObjetoAtualizado != null) {
				if(pacienteExistente.getCodigo() == codigoDoObjetoAtualizado) {
					return false;
				}
			}
			throw new RecursoRgDuplicadoException(Long.toString(pacienteExistente.getCodigo()));
		}else if(!listaDeUsuarios.isEmpty()) {
			throw new RgJaCadastradoException("Um usuário com o rg: '"+objeto.getrG()+"' já existe");
		}
		
		return false;
		
	}
	
	public void deletar (Long codigo)   {
		Paciente objetoAtualizado = buscarPorCodigo(codigo);
		try {
			repository.deleteById(codigo);
			if(objetoAtualizado.getFotoPerfil() != null) {
				if( objetoAtualizado.getFotoPerfil() != ""){
					s3Service.remover(objetoAtualizado.getFotoPerfil());
				}
				
			}

		} catch (DataIntegrityViolationException e) {
			lancarIntegridadeDeDadosException(e);
		}
	}

	public void lancarIntegridadeDeDadosException(DataIntegrityViolationException e) {
		throw new IntegridadeDeDadosException(
				"Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro.");
	}
	
	
	
	private Paciente obterPacienteExistente(List<Paciente> listaDeObjetos) {
		return listaDeObjetos.get(PRIMEIRO_ITEM);
	}
	
	
	
	public Paciente fromDTO(PacienteNovoDTO objetoDTO) {
		Paciente paciente = new Paciente(objetoDTO.getNome(), objetoDTO.getDataNascimento(), 
				objetoDTO.getcPF(), objetoDTO.getrG(), EstadoCivil.toEnum(objetoDTO.getEstadoCivil()), 
				Sexo.toEnum(objetoDTO.getSexo()), objetoDTO.getEmail(), objetoDTO.getProfissao(), 
				objetoDTO.getFotoPerfil(), objetoDTO.getUrlDaFoto());
		
		Cidade cidade = cidadeRepository.getOne(objetoDTO.getCodigoCidade());
		
		Endereco endereco = new Endereco(objetoDTO.getLogradouro(), objetoDTO.getNumero(), 
				objetoDTO.getBairro(), objetoDTO.getCEP(), objetoDTO.getComplemento(), cidade);
		
		paciente.setEndereco(endereco);
		
		Telefone telefonePrincipal = new Telefone(objetoDTO.getTelefonePrincipal(), paciente);
		
		paciente.getTelefones().add(telefonePrincipal);
		
		if(objetoDTO.getTelefone2() != null) {
			Telefone telefone2 = new Telefone(objetoDTO.getTelefone2(), paciente);
			
			paciente.getTelefones().add(telefone2);
		}

		return paciente;
	}
	
	public Foto enviarFotoDoPaciente(MultipartFile multipartFile) {
		return s3Service.enviarArquivo(multipartFile);
	}
	
	public List<Agendamento> buscarAgendamentosDoPacientePeloCodigo(Long codigo){
		return agendamentoRepository.buscarPorCodigoPaciente(codigo);
	}
	
	public List<Orcamento> buscarOrcamentosDoPacientePeloCodigo(Long codigo){
		OrcamentoFilter filter = new OrcamentoFilter();
		filter.setCodigoPaciente(codigo);
		return orcamentoRepository.filtrar(filter);
	}
	
	public List<ProcedimentoPrevisto> buscarProcedimentosPrevistosPeloCodigoDoPacienteEPeloFinalizado(Long codigo, Boolean finalizado){
		ProcedimentoPrevistoFilter filter = new ProcedimentoPrevistoFilter();
		filter.setCodigoPaciente(codigo);
		filter.setFinalizado(finalizado);
		return procedimentoPrevistoRepository.filtrar(filter);
		
	}
	
	private List<Dente> criarDentes(Paciente p){
		int[] numDentes = {18,17,16,15,14,13,12,11,21,22,23,24,25,26,27,28,48,47,46,45,44,43,42,41,31,32,33,34,35,36,37,38};
		
		List<Dente> dentes = new ArrayList<Dente>();
		for(int i = 0; i<32; i++) {
			Dente dente = new Dente();
			dente.setExistente(true);
			dente.setNumero(numDentes[i]);
			dente.setPaciente(p);
			dente.setObservacao("");
			
			dentes.add(dente);
		}
		
		return dentes;
	}
	
	public Paciente atualizarOdontograma(OdontogramaResumoDTO objetoDTO, Long codPaciente) throws OdontogramaInvalidoException {
		Paciente paciente = buscarPorCodigo(codPaciente);
		
		if(objetoDTO.getDentes().size() != paciente.getDentes().size()) {
			throw new OdontogramaInvalidoException("O odontograma está inválido");
		}
		
		for(int i = 0; i<paciente.getDentes().size(); i++) {
			if(objetoDTO.getDentes().get(i).getObservacao() != null)
				paciente.getDentes().get(i).setObservacao(objetoDTO.getDentes().get(i).getObservacao());
			if(objetoDTO.getDentes().get(i).getExistente() != null)
				paciente.getDentes().get(i).setExistente(objetoDTO.getDentes().get(i).getExistente());
		}
		
		paciente.setFormaRosto(FormaDoRosto.toEnum(objetoDTO.getFormaRosto()));
		paciente.setEscalaDente(objetoDTO.getEscalaDente());
		paciente.setCorDente(objetoDTO.getCorDente());
		
		return repository.save(paciente);
		
	}
	
}
