package com.projeto.dentalhelper.resources;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.domains.Pagamento;
import com.projeto.dentalhelper.domains.enums.TipoPagamento;
import com.projeto.dentalhelper.dtos.AgendamentoDashBoardDTO;
import com.projeto.dentalhelper.dtos.DashBoardDTO;
import com.projeto.dentalhelper.dtos.DespesaRecebimentoDashBoardDTO;
import com.projeto.dentalhelper.dtos.RecebimentoDespesaDashBoardGraficoDTO;
import com.projeto.dentalhelper.repositories.filter.AgendamentoFilter;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;
import com.projeto.dentalhelper.repositories.filter.PagamentoFilter;
import com.projeto.dentalhelper.resources.api.DashBoardApi;
import com.projeto.dentalhelper.services.AgendamentoService;
import com.projeto.dentalhelper.services.DespesaService;
import com.projeto.dentalhelper.services.PagamentoService;

@RestController
public class DashboardResource implements DashBoardApi{
	
	@Autowired
	private DespesaService despesaService;
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@Autowired
	private PagamentoService pagamentoService;
	

	@Override
	public ResponseEntity<DashBoardDTO> getInformacaoes() {
		
		DashBoardDTO dashBoardDTO = new DashBoardDTO();

		AgendamentoFilter agendamentoFilter = new AgendamentoFilter();
		agendamentoFilter.setDataAgendamento(new Date());
		
		List<Agendamento> agendamentos= agendamentoService.filtrar(agendamentoFilter);
		List<AgendamentoDashBoardDTO> agendamentosDTO = new ArrayList<AgendamentoDashBoardDTO>();
		for(Agendamento a: agendamentos) {
			agendamentosDTO.add(new AgendamentoDashBoardDTO(a));
		}
		
		dashBoardDTO.setAgendamentos(agendamentosDTO);
		
		List<DespesaRecebimentoDashBoardDTO> pagamentosDTO = new ArrayList<DespesaRecebimentoDashBoardDTO>();
		
		PagamentoFilter pagamentoFilter = new PagamentoFilter();
		pagamentoFilter.setDataPagamento(new Date());
		pagamentoFilter.setTipo(TipoPagamento.RECEBIMENTO.getCodigo());
		
		List<Pagamento> pagamentos = pagamentoService.filtrar(pagamentoFilter);
		for(Pagamento p: pagamentos) {
			pagamentosDTO.add(new DespesaRecebimentoDashBoardDTO(p));
		}
		
		
		DespesaFilter despesaFilter = new DespesaFilter();
		despesaFilter.setDataPagamento(new Date());
		
		List<Despesa> despesas = despesaService.filtrar(despesaFilter);
		for(Despesa d: despesas) {
			pagamentosDTO.add(new DespesaRecebimentoDashBoardDTO(d));
		}
		
		dashBoardDTO.setPagamentos(pagamentosDTO);
		
		
		List<Date> datas = retornarDatasDaSemanaSemDomingo(new Date());
		List<RecebimentoDespesaDashBoardGraficoDTO> pagamentosGrafico = new ArrayList<RecebimentoDespesaDashBoardGraficoDTO>();
		
		
		
		for(Date d: datas) {
			RecebimentoDespesaDashBoardGraficoDTO pagamentoGrafico = new RecebimentoDespesaDashBoardGraficoDTO();
			pagamentoGrafico.setDataSemana(d);
			
			pagamentoFilter.setDataPagamento(d);
			pagamentos = pagamentoService.filtrar(pagamentoFilter);
			
			despesaFilter.setDataPagamento(d);
			despesas = despesaService.filtrar(despesaFilter);
			
			Float recebimento = new Float(0);
			Float despesa = new Float(0);
			
			for(Pagamento p: pagamentos) {
				recebimento+=p.getValor();
			}
			
			for(Despesa de: despesas) {
				despesa+=de.getValor();
			}
			
			pagamentoGrafico.setDespesa(despesa);
			pagamentoGrafico.setRecebimento(recebimento);
			
			pagamentosGrafico.add(pagamentoGrafico);
			
			
		}
		
		dashBoardDTO.setPagamentosGrafico(pagamentosGrafico);
		
		
		
		return ResponseEntity.ok(dashBoardDTO);
		
		
		
		
	}
	
	
	private List<Date> retornarDatasDaSemanaSemDomingo(Date data){
		Calendar calendar = Calendar.getInstance ();
		
        calendar.setTime (data);
        calendar.set (Calendar.HOUR_OF_DAY, 1);
        calendar.set (Calendar.MINUTE, 0);
        calendar.set (Calendar.SECOND, 0); calendar.set (Calendar.MILLISECOND, 0);
        
        List<Date> ret = new ArrayList<Date>();
        
        int diaSemana = calendar.get (Calendar.DAY_OF_WEEK);
        Calendar calFirst = (Calendar) calendar.clone();
        
        //monday setando o primeiro dia como segunda
        calFirst.add (Calendar.DATE, -diaSemana + Calendar.MONDAY); 
        
        //6 pq não conta o domingo já q o primeiro dia é segunda
        for (int i = 0; i < 6; ++i) {
            ret.add (calFirst.getTime());
            calFirst.add (Calendar.DATE, 1);
        }
        
        return ret;
	}
	

}
