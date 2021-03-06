package com.projeto.dentalhelper.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.dtos.AgendamentoResumoDTO;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;
import com.projeto.dentalhelper.resources.api.AgendamentoApi;
import com.projeto.dentalhelper.services.AgendamentoService;
import com.projeto.dentalhelper.services.exceptions.AgendamentoJaCadastradoNoHorarioRuntimeException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoRunTimeException;
import com.projeto.dentalhelper.services.exceptions.DataAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.DataAgendamentoInvalidaRuntimeException;
import com.projeto.dentalhelper.services.exceptions.HoraAgendamentoInvalidaException;
import com.projeto.dentalhelper.services.exceptions.HoraAgendamentoInvalidaRuntimeException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoNaoAprovadoException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoNaoAprovadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ProcedimentoFinalizadoException;
import com.projeto.dentalhelper.services.exceptions.ProcedimentoFinalizadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ProcedimentoNaoEstaEmOrcamentoException;
import com.projeto.dentalhelper.services.exceptions.ProcedimentoNaoEstaEmOrcamentoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class AgendamentoResource extends AbstractResource<Agendamento, AgendamentoService> implements AgendamentoApi {

	@Override
	public ResponseEntity<Agendamento> post(@Valid AgendamentoNovoDTO objeto, HttpServletResponse response)
			throws ServiceApplicationException {

		Agendamento objetoSalvo = null;

		try {
			objetoSalvo = service.fromDTO(objeto);
			objetoSalvo = salvar(objetoSalvo);
		} catch (HoraAgendamentoInvalidaException e) {
			throw new HoraAgendamentoInvalidaRuntimeException(e.getMessage());
		} catch (DataAgendamentoInvalidaException e) {
			throw new DataAgendamentoInvalidaRuntimeException(e.getMessage());
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoRunTimeException(e.getMessage());
		} catch (ProcedimentoNaoEstaEmOrcamentoException e) {
			throw new ProcedimentoNaoEstaEmOrcamentoRuntimeException(e.getMessage());
		} catch (OrcamentoNaoAprovadoException e) {
			throw new OrcamentoNaoAprovadoRuntimeException(e.getMessage());
		} catch (ProcedimentoFinalizadoException e) {
			throw new ProcedimentoFinalizadoRuntimeException(e.getMessage());
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/agendamentos");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private Agendamento salvar(Agendamento objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	public ResponseEntity<List<AgendamentoResumoDTO>> getByFilter(AgendamentoFilter filter) {
		List<Agendamento> objetos = service.filtrar(filter);
		adicionarLinks(objetos);
		List<AgendamentoResumoDTO> agendamentoResumoDTO = objetos.stream().map(agendamento -> new AgendamentoResumoDTO(agendamento))
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(agendamentoResumoDTO);
		
		
	}
	
	public ResponseEntity<Agendamento> getByCodigo(@PathVariable Long codigo) {
		Agendamento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<Agendamento> put(Long codigo, @Valid @RequestBody AgendamentoNovoDTO objetoModificado)
			throws ServiceApplicationException {
		Agendamento objetoEditado = null;
		try {
			objetoEditado = service.fromDTO(objetoModificado);
			objetoEditado = atualizar(codigo, objetoEditado);
		} catch (HoraAgendamentoInvalidaException e) {
			throw new HoraAgendamentoInvalidaRuntimeException(e.getMessage());
		} catch (DataAgendamentoInvalidaException e) {
			throw new DataAgendamentoInvalidaRuntimeException(e.getMessage());
		} catch (DadoInvalidoException e) {
			throw new DadoInvalidoRunTimeException(e.getMessage());
		} catch (ProcedimentoNaoEstaEmOrcamentoException e) {
			throw new ProcedimentoNaoEstaEmOrcamentoRuntimeException(e.getMessage());
		} catch (OrcamentoNaoAprovadoException e) {
			throw new OrcamentoNaoAprovadoRuntimeException(e.getMessage());
		} catch (ProcedimentoFinalizadoException e) {
			throw new ProcedimentoFinalizadoRuntimeException(e.getMessage());
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
	}

	private Agendamento atualizar(Long codigo, Agendamento objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}
	
	protected void lancarExceptionComLocation(ServiceApplicationException e) {
		Agendamento agendamentoExistente = service.buscarPorCodigo(Long.parseLong(e.getMessage()));
		adicionarLink(agendamentoExistente, agendamentoExistente.getCodigo());

		throw new AgendamentoJaCadastradoNoHorarioRuntimeException("Já existe um agendamento nesse horário",
				agendamentoExistente.getId().getHref());
	}

	@Override
	public ResponseEntity<AgendamentoNovoDTO> getByCodigoForEdit(Long codigo) {
		Agendamento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		AgendamentoNovoDTO agendamentoDTO = new AgendamentoNovoDTO(objeto);
		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(agendamentoDTO);
	}

	@Override
	public ResponseEntity<Agendamento> atualizarStatus(@PathVariable Long codigo, @RequestBody Integer status) throws ServiceApplicationException {
		Agendamento objetoBuscado = service.atualizarStatus(codigo, status);
		return ResponseEntity.ok(objetoBuscado);
	}

//	@Override
//	public ResponseEntity<List<AgendamentoDashBoardDTO>> buscarParaDashBoard(Date data) {
//		AgendamentoFilter filter = new AgendamentoFilter();
//		filter.setDataAgendamento(data);
//		
//		List<Agendamento> agendamentos= service.filtrar(filter);
//		List<AgendamentoDashBoardDTO> agendamentosDTO = new ArrayList<AgendamentoDashBoardDTO>();
//		for(Agendamento a: agendamentos) {
//			agendamentosDTO.add(new AgendamentoDashBoardDTO(a));
//		}
//		
//		return ResponseEntity.ok(agendamentosDTO);
//	}
	
	
	
	

}
