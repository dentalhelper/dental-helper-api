package com.projeto.dentalhelper.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;
import com.projeto.dentalhelper.resources.api.MaterialApi;
import com.projeto.dentalhelper.services.MaterialService;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@RestController
public class MaterialResource extends AbstractResource<Material, MaterialService> implements MaterialApi {

	public ResponseEntity<Material> post(@Valid Material objeto, HttpServletResponse response)
			throws ServiceApplicationException {

		Material objetoSalvo = service.salvar(objeto);

		Long codigo = objetoSalvo.getCodigo();
		adicionarHeaderLocation(response, codigo, "/materiais");
		adicionarLink(objetoSalvo, codigo);
		return ResponseEntity.status(HttpStatus.CREATED).body(objetoSalvo);
	}

	public List<Material> getByFilter(MaterialFilter filter) {
		List<Material> objetos = service.filtrar(filter);
		return adicionarLinks(objetos);
	}

	public ResponseEntity<Material> getByCodigo(@PathVariable Long codigo) {
		Material objeto = service.buscarPorCodigo(codigo);
		adicionarLink(objeto, codigo);
		return objeto != null ? ResponseEntity.ok(objeto) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<Material> put(Long codigo, @Valid Material objetoModificado)
			throws ServiceApplicationException {
		Material objetoEditado = service.atualizar(codigo, objetoModificado);
		return ResponseEntity.ok(objetoEditado);
	}

	public ResponseEntity<Void> delete(Long codigo) {
		service.deletar(codigo);
		return ResponseEntity.noContent().header("Entity", Long.toString(codigo)).build();
	}

}
