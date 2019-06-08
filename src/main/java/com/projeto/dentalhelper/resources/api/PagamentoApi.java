package com.projeto.dentalhelper.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.dtos.PagamentoRecebimentoNovoDTO;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "pagamentos")
public interface PagamentoApi {
	
	@ApiOperation(value="Busca todos os pagamentos")
	@GetMapping
	public List<Pagamento> getAll();
	
	
	@ApiOperation(value = "Salva um pagamento de recebimento")
	@PostMapping(value = "/novo")
	public ResponseEntity<Pagamento> post(@Valid @RequestBody PagamentoRecebimentoNovoDTO objeto, HttpServletResponse response)
			throws ServiceApplicationException;
	
	@ApiOperation(value = "Busca um pagamento por código")
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Pagamento> getByCodigo(@PathVariable Long codigo);
	
	@ApiOperation(value = "Atualiza um pagamento de recebimento")
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<Pagamento> put(@PathVariable Long codigo,
			@Valid @RequestBody PagamentoRecebimentoNovoDTO objetoModificado) throws ServiceApplicationException;
	
	@ApiOperation(value = "Deleta um pagamento")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Integridade de dados violada, não é possível excluir um recurso que está relacionado à outro."),
			@ApiResponse(code = 404, message = "Código inexistente.") })
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'ASSISTENTE')")
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
