package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.repositories.DespesaRepository;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class DespesaService extends AbstractService<Despesa, DespesaRepository>{
	
	@Autowired
	private PagamentoService pagamentoService;
	
	public List<Despesa> filtrar(DespesaFilter filter){
		
		return repository.filtrar(filter);
		
	}
	
	
	@Override
	public Despesa salvar(Despesa objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		
		if(objeto.getDescricao() == null || objeto.getDescricao() == "") {
			objeto.setDescricao(objeto.getCategoria().getNome());
		}

		objeto.getPagamento().setValor(objeto.getValor());
		
		Pagamento pagamento = objeto.getPagamento();
		pagamento.setValor(objeto.getValor());
		
		objeto.setPagamento(pagamentoService.salvar(pagamento));
		
		
		return repository.save(objeto);
	}
	
	@Override
	public Despesa atualizar(Long codigo, Despesa objetoModificado) {	
		objetoModificado.getPagamento().setValor(objetoModificado.getValor());
		Despesa objetoAtualizado = buscarPorCodigo(codigo);
		
		Pagamento pagamento = pagamentoService.buscarPorCodigo(objetoAtualizado.getPagamento().getCodigo());
		
		pagamento = pagamentoService.atualizar(pagamento.getCodigo(), objetoModificado.getPagamento());
		
		objetoModificado.setPagamento(pagamento);
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}
	
//	@Override
//	public void deletar (Long codigo)   {
//		Despesa despesa = buscarPorCodigo(codigo);
//		Pagamento pagamento = despesa.getPagamento();
//		despesa.setPagamento(null);
//		
//		repository.save(despesa);
//		
//		try {
//			repository.deleteById(codigo);
//			if(pagamento != null) {
//				pagamentoService.deletar(pagamento.getCodigo());
//			}
//
//		} catch (DataIntegrityViolationException e) {
//			lancarIntegridadeDeDadosException(e);
//		}
//	}
	
	

}
