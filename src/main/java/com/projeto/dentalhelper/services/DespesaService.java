package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.CategoriaDespesa;
import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.enums.TipoPagamento;
import com.projeto.dentalhelper.repositories.DespesaRepository;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class DespesaService extends AbstractService<Despesa, DespesaRepository>{
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private CategoriaDespesaService categoriaService;
	
	public List<Despesa> filtrar(DespesaFilter filter){
		
		return repository.filtrar(filter);
		
	}
	
	
	@Override
	public Despesa salvar(Despesa objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		if(objeto.getDescricao() == null) {
			CategoriaDespesa cat = categoriaService.buscarPorCodigo(objeto.getCategoria().getCodigo());
			objeto.setDescricao(cat.getNome());
		}

		objeto.getPagamento().setValor(objeto.getValor());
		
		
		Pagamento pagamento = objeto.getPagamento();
		pagamento.setValor(objeto.getValor());
		pagamento.setTipo(TipoPagamento.DESPESA);
		
		objeto.setPagamento(pagamentoService.salvar(pagamento));
		
		
		return repository.save(objeto);
	}
	
	@Override
	public Despesa atualizar(Long codigo, Despesa objetoModificado) throws ServiceApplicationException{	
		
		objetoModificado.getPagamento().setValor(objetoModificado.getValor());
		objetoModificado.getPagamento().setTipo(TipoPagamento.DESPESA);
		
		if(objetoModificado.getDescricao() == null) {
			CategoriaDespesa cat = categoriaService.buscarPorCodigo(objetoModificado.getCategoria().getCodigo());
			objetoModificado.setDescricao(cat.getNome());
		}
		
		
		Despesa objetoAtualizado = buscarPorCodigo(codigo);
		
		Pagamento pagamento = pagamentoService.buscarPorCodigo(objetoAtualizado.getPagamento().getCodigo());
		
		pagamento = pagamentoService.atualizar(pagamento.getCodigo(), objetoModificado.getPagamento());
		
		objetoModificado.setPagamento(pagamento);
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}
	
	
	

}
