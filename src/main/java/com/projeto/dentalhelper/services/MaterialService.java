package com.projeto.dentalhelper.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.MaterialRepository;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class MaterialService extends AbstractService<Material, MaterialRepository> {

	@Override
	public Material salvar(Material objeto) throws ServiceApplicationException {
		objeto.setCodigo(null);
		objeto.getAtributoMateriais().forEach(atributo -> atributo.setMaterial(objeto));
		return repository.save(objeto);
	}

	@Override
	public Material atualizar(Long codigo, Material objetoModificado) throws ServiceApplicationException {
		Material objetoAtualizado = buscarPorCodigo(codigo);

		objetoAtualizado.getAtributoMateriais().clear();
		objetoAtualizado.getAtributoMateriais().addAll(objetoModificado.getAtributoMateriais());

		objetoAtualizado.getAtributoMateriais().forEach(atributo -> atributo.setMaterial(objetoAtualizado));

		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo", "atributoMateriais");
		return repository.save(objetoAtualizado);
	}

	public List<Material> filtrar(MaterialFilter filter) {
		return repository.filtrar(filter);
	}
}
