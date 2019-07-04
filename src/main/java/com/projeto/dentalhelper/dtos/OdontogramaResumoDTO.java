package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Dente;
import com.projeto.dentalhelper.domains.Paciente;

public class OdontogramaResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codPaciente;
	
	private List<DenteOdontogramaResumoDTO> dentes = new ArrayList<DenteOdontogramaResumoDTO>();
	
	@NotBlank
	private String escalaDente;
	
	@NotBlank
	private String corDente;
	
	@NotNull
	private Integer formaRosto;
	

	public OdontogramaResumoDTO() {
	}
	
	public OdontogramaResumoDTO(Paciente p) {
		this.codPaciente = p.getCodigo();
		this.dentes = adicionarDentes(p.getDentes());
		this.escalaDente = p.getEscalaDente();
		this.corDente = p.getCorDente();
		this.formaRosto = p.getFormaRosto().getCodigo();
	}

	public Long getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(Long codPaciente) {
		this.codPaciente = codPaciente;
	}

	public List<DenteOdontogramaResumoDTO> getDentes() {
		return dentes;
	}

	public void setDentes(List<DenteOdontogramaResumoDTO> dentes) {
		this.dentes = dentes;
	}
	
	
	
	public String getEscalaDente() {
		return escalaDente;
	}

	public void setEscalaDente(String escalaDente) {
		this.escalaDente = escalaDente;
	}

	public String getCorDente() {
		return corDente;
	}

	public void setCorDente(String corDente) {
		this.corDente = corDente;
	}

	public Integer getFormaRosto() {
		return formaRosto;
	}

	public void setFormaRosto(Integer formaRosto) {
		this.formaRosto = formaRosto;
	}

	private List<DenteOdontogramaResumoDTO> adicionarDentes(List<Dente> dentes){
		List<DenteOdontogramaResumoDTO> dentesDTO = new ArrayList<DenteOdontogramaResumoDTO>();
		for(Dente d: dentes) {
			dentesDTO.add(new DenteOdontogramaResumoDTO(d));
		}
		return dentesDTO;
	}
	
	

}
