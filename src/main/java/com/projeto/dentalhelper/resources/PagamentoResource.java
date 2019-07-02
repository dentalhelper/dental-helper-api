package com.projeto.dentalhelper.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.enums.TipoPagamento;
import com.projeto.dentalhelper.dtos.DespesaRecebimentoDashBoardDTO;
import com.projeto.dentalhelper.dtos.PagamentoRecebimentoNovoDTO;
import com.projeto.dentalhelper.repositories.PagamentoRepository;
import com.projeto.dentalhelper.repositories.filter.PagamentoFilter;
import com.projeto.dentalhelper.resources.api.PagamentoApi;
import com.projeto.dentalhelper.services.PagamentoService;
import com.projeto.dentalhelper.services.exceptions.DespesaNaoPodeSerApagadaException;
import com.projeto.dentalhelper.services.exceptions.DespesaNaoPodeSerApagadaRuntimeException;
import com.projeto.dentalhelper.services.exceptions.DespesaNaoPodeSerEditadaException;
import com.projeto.dentalhelper.services.exceptions.DespesaNaoPodeSerEditadaRuntimeException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoNaoAprovadoException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoNaoAprovadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.PagamentoCanceladoException;
import com.projeto.dentalhelper.services.exceptions.PagamentoCanceladoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.PagamentoFinalizadoException;
import com.projeto.dentalhelper.services.exceptions.PagamentoFinalizadoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.PagamentoSuperaValorTotalDoOrcamentoException;
import com.projeto.dentalhelper.services.exceptions.PagamentoSuperaValorTotalDoOrcamentoRuntimeException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class PagamentoResource extends AbstractResource<Pagamento, PagamentoService> implements PagamentoApi{

	@Autowired
	private PagamentoRepository repository;	
	
	@Override
	public List<Pagamento> getAll() {
		return repository.findAll();
	}

	@Override
	public ResponseEntity<Pagamento> post(@Valid PagamentoRecebimentoNovoDTO objeto, HttpServletResponse response) throws ServiceApplicationException {
		Pagamento objetoSalvo = null;

		try {
			objetoSalvo = service.fromDTO(objeto);
			objetoSalvo = salvar(objetoSalvo);
		} catch (PagamentoSuperaValorTotalDoOrcamentoException e) {
			throw new PagamentoSuperaValorTotalDoOrcamentoRuntimeException(e.getMessage());
		} catch (DespesaNaoPodeSerEditadaException e) {
			throw new DespesaNaoPodeSerEditadaRuntimeException(e.getMessage());
		} catch (DespesaNaoPodeSerApagadaException e) {
			throw new DespesaNaoPodeSerApagadaRuntimeException(e.getMessage());
		} catch (OrcamentoNaoAprovadoException e) {
			throw new OrcamentoNaoAprovadoRuntimeException(e.getMessage());
		} catch (PagamentoFinalizadoException e) {
			throw new PagamentoFinalizadoRuntimeException(e.getMessage());
		} catch (PagamentoCanceladoException e) {
			throw new PagamentoCanceladoRuntimeException(e.getMessage());	
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/pagamentos");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}
	
	private Pagamento salvar(Pagamento objeto) throws ServiceApplicationException {
		return service.salvarRecebimento(objeto);
	}

	@Override
	public ResponseEntity<Pagamento> getByCodigo(Long codigo) {
		Pagamento objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Pagamento> put(Long codigo, @Valid PagamentoRecebimentoNovoDTO objetoModificado) throws ServiceApplicationException {
		Pagamento objetoEditado = null;
		try {
			objetoEditado = service.fromDTO(objetoModificado);
			objetoEditado = atualizar(codigo, objetoEditado);
		} catch (PagamentoSuperaValorTotalDoOrcamentoException e) {
			throw new PagamentoSuperaValorTotalDoOrcamentoRuntimeException(e.getMessage());
		} catch (DespesaNaoPodeSerEditadaException e) {
			throw new DespesaNaoPodeSerEditadaRuntimeException(e.getMessage());
		} catch (DespesaNaoPodeSerApagadaException e) {
			throw new DespesaNaoPodeSerApagadaRuntimeException(e.getMessage());
		} catch (PagamentoFinalizadoException e) {
			throw new PagamentoFinalizadoRuntimeException(e.getMessage());
		} catch (PagamentoCanceladoException e) {
			throw new PagamentoCanceladoRuntimeException(e.getMessage());
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}
		return ResponseEntity.ok(objetoEditado);
		
	}
	
	private Pagamento atualizar(Long codigo, Pagamento objetoModificado) throws ServiceApplicationException {
		return service.atualizarRecebimento(codigo, objetoModificado);
	}

	@Override
	public ResponseEntity<Void> delete(Long codigo) {
		try {
			service.deletarRecebimento(codigo);
		} catch (DespesaNaoPodeSerApagadaException e) {
			throw new DespesaNaoPodeSerApagadaRuntimeException(e.getMessage());
		} catch (ServiceApplicationException e) {
			lancarExceptionComLocation(e);
		}	
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

	@Override
	public ResponseEntity<List<DespesaRecebimentoDashBoardDTO>> filtrarparaDashBoard(Date data) {
		PagamentoFilter filter = new PagamentoFilter();
		filter.setDataPagamento(data);
		filter.setTipo(TipoPagamento.RECEBIMENTO.getCodigo());
		
		List<Pagamento> pagamentos = service.filtrar(filter);
		List<DespesaRecebimentoDashBoardDTO> pagamentosDTO = new ArrayList<DespesaRecebimentoDashBoardDTO>();
		for(Pagamento p: pagamentos) {
			pagamentosDTO.add(new DespesaRecebimentoDashBoardDTO(p));
		}
		
		return ResponseEntity.ok(pagamentosDTO);
	}

}
