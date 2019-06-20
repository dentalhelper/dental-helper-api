package com.projeto.dentalhelper.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.projeto.dentalhelper.domains.Dente;
import com.projeto.dentalhelper.domains.Paciente;

public class OdontogramaResumoDTO extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codPaciente;
	
	private List<DenteOdontogramaResumoDTO> dentes = new ArrayList<DenteOdontogramaResumoDTO>();
	

	public OdontogramaResumoDTO() {
	}
	
	public OdontogramaResumoDTO(Paciente p) {
		this.codPaciente = p.getCodigo();
		this.dentes = adicionarDentes(p.getDentes());
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
	
	private List<DenteOdontogramaResumoDTO> adicionarDentes(List<Dente> dentes){
		List<DenteOdontogramaResumoDTO> dentesDTO = new ArrayList<DenteOdontogramaResumoDTO>();
		for(Dente d: dentes) {
			dentesDTO.add(new DenteOdontogramaResumoDTO(d));
		}
		return dentesDTO;
	}
	
	

}
