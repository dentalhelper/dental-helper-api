package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.projeto.dentalhelper.domains.Agendamento;
import com.projeto.dentalhelper.domains.Paciente;

public class AgendamentoDashBoardDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String nomeProcedimento;
	private String horaInicio;
	
	//paciente
	private String nomePaciente;
	private String urlFoto;
	private Date dataNascimento;
	private String telefonePrincipal;
	private Integer sexo;
	private Long codPaciente;

	
	
	public AgendamentoDashBoardDTO(Agendamento a) {
		Paciente paciente = a.getOrcamento().getPaciente();
		
		this.status = a.getStatusAgendamento().getCodigo();
		this.nomePaciente = paciente.getNome();
		this.nomeProcedimento = a.getProcedimentoPrevisto().getProcedimento().getNome();
		this.horaInicio = converterHoraParaString(a.getHoraInicio());
		this.urlFoto = paciente.getUrlDaFoto();
		this.dataNascimento = paciente.getDataNascimento();
		this.telefonePrincipal = paciente.getTelefonePrincipal();
		this.sexo = paciente.getSexo().getCodigo();
		this.codPaciente = paciente.getCodigo();
	}
	
	
	
	
	public AgendamentoDashBoardDTO() {

	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getNomeProcedimento() {
		return nomeProcedimento;
	}
	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}
	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}
	public Integer getSexo() {
		return sexo;
	}
	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}
	
	private String converterHoraParaString(Date hora) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
        return dateFormat.format(hora);  
	}

	public Long getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(Long codPaciente) {
		this.codPaciente = codPaciente;
	}
	
	
	

}
