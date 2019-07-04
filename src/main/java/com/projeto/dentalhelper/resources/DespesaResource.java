package com.projeto.dentalhelper.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.dtos.DespesaRecebimentoDashBoardDTO;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.resources.api.DespesaApi;
import com.projeto.dentalhelper.services.DespesaService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class DespesaResource extends AbstractResource<Despesa, DespesaService> implements DespesaApi {

	public ResponseEntity<Despesa> post(@Valid Despesa objeto, HttpServletResponse response)
			throws ServiceApplicationException {

		Despesa objetoSalvo = salvar(objeto);

		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/despesas");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	private Despesa salvar(Despesa objeto) throws ServiceApplicationException {
		return service.salvar(objeto);
	}

	public List<Despesa> getByFilter(DespesaFilter filter) {
		List<Despesa> objetos = service.filtrar(filter);
		return adicionarLinks(objetos);
	}

	public ResponseEntity<Despesa> getByCodigo(@PathVariable Long codigo) {
		Despesa objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<Despesa> put(Long codigo, @Valid Despesa objetoModificado)
			throws ServiceApplicationException {
		Despesa objetoEditado = atualizar(codigo, objetoModificado);
		return ResponseEntity.ok(objetoEditado);
	}

	private Despesa atualizar(Long codigo, Despesa objetoModificado) throws ServiceApplicationException {
		return service.atualizar(codigo, objetoModificado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

	@Override
	public ResponseEntity<List<DespesaRecebimentoDashBoardDTO>> filtrarparaDashBoard(Date data) {
		DespesaFilter filter = new DespesaFilter();
		filter.setDataPagamento(data);
		
		List<Despesa> despesas = service.filtrar(filter);
		List<DespesaRecebimentoDashBoardDTO> despesasDTO = new ArrayList<DespesaRecebimentoDashBoardDTO>();
		for(Despesa d: despesas) {
			despesasDTO.add(new DespesaRecebimentoDashBoardDTO(d));
		}
		return ResponseEntity.ok(despesasDTO);
		
	}

}
