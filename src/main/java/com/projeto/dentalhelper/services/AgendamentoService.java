package com.projeto.dentalhelper.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.domains.Paciente;
import com.projeto.dentalhelper.domains.Procedimento;
import com.projeto.dentalhelper.domains.enums.StatusAgendamento;
import com.projeto.dentalhelper.dtos.AgendamentoNovoDTO;
import com.projeto.dentalhelper.repositories.AgendamentoRepository;
import com.projeto.dentalhelper.services.exceptions.DadoInvalidoException;

@Service
public class AgendamentoService extends AbstractService<Agendamento, AgendamentoRepository>{
	
	
	
	
	
	
	public Agendamento fromDTO(AgendamentoNovoDTO objetoDTO) throws DadoInvalidoException {
		
		
		Date horaInicio = converterStringParaHora(objetoDTO.getHoraInicio());
		Date horaFim = converterStringParaHora(objetoDTO.getHoraFim());
		
		Paciente paciente = new Paciente();
		paciente.setCodigo(objetoDTO.getCodigoPaciente());
		
		Procedimento procedimento = new Procedimento();
		procedimento.setCodigo(objetoDTO.getCodigoProcedimento());
		
		
		Agendamento agendamento = new Agendamento(objetoDTO.getDataAgendamento(), horaInicio, horaFim, StatusAgendamento.toEnum(objetoDTO.getStatusAgendamento()), 
				objetoDTO.getObservacao(), objetoDTO.getPrimeiraAvalicao(), paciente , procedimento);
				
				
				
		return agendamento;
	}
	
	
	private Date converterStringParaHora(String hora) throws DadoInvalidoException {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
	    try {
			return dateFormat.parse(hora);
		} catch (ParseException e) {
			throw new DadoInvalidoException("Erro na conversão da hora: " + hora);
		}
	}

}
