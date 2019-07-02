package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Orcamento;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.enums.StatusPagamento;
import com.projeto.dentalhelper.domains.enums.TipoPagamento;
import com.projeto.dentalhelper.dtos.PagamentoRecebimentoNovoDTO;
import com.projeto.dentalhelper.repositories.PagamentoRepository;
import com.projeto.dentalhelper.repositories.filter.PagamentoFilter;
import com.projeto.dentalhelper.services.exceptions.DespesaNaoPodeSerApagadaException;
import com.projeto.dentalhelper.services.exceptions.DespesaNaoPodeSerEditadaException;
import com.projeto.dentalhelper.services.exceptions.OrcamentoNaoAprovadoException;
import com.projeto.dentalhelper.services.exceptions.PagamentoCanceladoException;
import com.projeto.dentalhelper.services.exceptions.PagamentoFinalizadoException;
import com.projeto.dentalhelper.services.exceptions.PagamentoSuperaValorTotalDoOrcamentoException;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class PagamentoService extends AbstractService<Pagamento, PagamentoRepository>{
	
	@Autowired
	private OrcamentoService orcamentoService;

	public Pagamento salvarRecebimento(Pagamento objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		if(objeto.getOrcamento().getStatus() == StatusPagamento.CANCELADO) {
			throw new PagamentoCanceladoException("O pagamento foi cancelado.");
		}
		if(objeto.getOrcamento().getStatus() == StatusPagamento.PAGO) {
			throw new PagamentoFinalizadoException("O pagamento já foi finalizado.");
		}
		valorPagamentoSuperaValorTotal(objeto.getOrcamento(), objeto);
		orcamentoEstaAprovado(objeto.getOrcamento());
		
		
		atualizarStatusDoOrcamento(objeto.getOrcamento(), objeto);
		
		return repository.save(objeto);
	}


	private void atualizarStatusDoOrcamento(Orcamento o, Pagamento p) {
		float valor = 0;
		
		for(Pagamento pagamento: o.getPagamentos()) {
			if(p.getCodigo() != pagamento.getCodigo())
				valor+=pagamento.getValor();
		}
		valor+=p.getValor();
		
		if(valor == o.getValorTotal()) {
			orcamentoService.atualizarStatusPagamento(o.getCodigo(), StatusPagamento.PAGO.getCodigo());
		}
	}


	public Pagamento atualizarRecebimento(Long codigo, Pagamento objetoModificado) throws ServiceApplicationException{
		Pagamento objetoAtualizado = buscarPorCodigo(codigo);
		objetoModificado.setCodigo(codigo);
		
		isDespesa(objetoAtualizado, true);
		
		if(objetoModificado.getOrcamento().getStatus() == StatusPagamento.CANCELADO) {
			throw new PagamentoCanceladoException("O pagamento foi cancelado.");
		}
		if(objetoModificado.getOrcamento().getStatus() == StatusPagamento.PAGO) {
			throw new PagamentoFinalizadoException("O pagamento já foi finalizado.");
		}
		
		
		valorPagamentoSuperaValorTotal(objetoAtualizado.getOrcamento(), objetoModificado);
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "orcamento", "tipo");
		return repository.save(objetoAtualizado);
	}
	
	public Pagamento fromDTO(PagamentoRecebimentoNovoDTO objetoDTO) {
		Orcamento orcamento = orcamentoService.buscarPorCodigo(objetoDTO.getCodOrcamento());
		
		Pagamento pagamento = new Pagamento(objetoDTO.getDataPagamento(), objetoDTO.getForma(), objetoDTO.getValor(), TipoPagamento.RECEBIMENTO.getCodigo(), orcamento);
		
		return pagamento;
	}


	public void deletarRecebimento (Long codigo) throws ServiceApplicationException   {
		Pagamento p = buscarPorCodigo(codigo);
		isDespesa(p, false);
		try {
			repository.deleteById(codigo);

		} catch (DataIntegrityViolationException e) {
			lancarIntegridadeDeDadosException(e);
		}
	}
	
	public boolean valorPagamentoSuperaValorTotal(Orcamento o, Pagamento p) throws PagamentoSuperaValorTotalDoOrcamentoException {
		float valor = 0;
		
		for(Pagamento pagamento: o.getPagamentos()) {
			if(p.getCodigo() != pagamento.getCodigo())
				valor+=pagamento.getValor();
		}
		valor+=p.getValor();
		
		if(valor > o.getValorTotal()) {
			throw new PagamentoSuperaValorTotalDoOrcamentoException("O valor dos pagamentos: "+"'"+valor+"'"
		+" supera o valor total do orçamento: "+"'"+o.getValorTotal()+"'"+". O valor desse pagamento é: "+"'"+p.getValor()+"'");
		}
		
		return false;
	}
	
	public boolean isDespesa(Pagamento p, boolean edicao) throws DespesaNaoPodeSerEditadaException, DespesaNaoPodeSerApagadaException {
		if(p.getTipo() == TipoPagamento.DESPESA) {
			if(edicao) {
				throw new DespesaNaoPodeSerEditadaException("Uma despesa não pode ser editada dessa forma");
			}
			else {
				throw new DespesaNaoPodeSerApagadaException("Uma despesa não pode ser apagada dessa forma");
			}
			
		}
		return false;
	}
	
	public void orcamentoEstaAprovado(Orcamento o) throws OrcamentoNaoAprovadoException {
		if(o.getAprovado() == false) {
			throw new OrcamentoNaoAprovadoException("Não poderá adicionar pagamentos enquanto o orçamento não for aprovado.");
		}
	}
	
	public List<Pagamento >filtrar(PagamentoFilter filter) {
		return repository.filtrar(filter);
	}
	
	
	
}
