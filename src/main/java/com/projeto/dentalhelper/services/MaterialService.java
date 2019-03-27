package com.projeto.dentalhelper.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dentalhelper.domains.AtributoMaterial;
import com.projeto.dentalhelper.domains.Material;
import com.projeto.dentalhelper.repositories.MaterialRepository;
import com.projeto.dentalhelper.repositories.filter.MaterialFilter;
import com.projeto.dentalhelper.services.exceptions.ServiceApplicationException;

@Service
public class MaterialService extends AbstractService<Material, MaterialRepository>{
	
	@Autowired
	private AtributoMaterialService atributoMaterialService;
	
	@Override
	public Material salvar(Material objeto) throws ServiceApplicationException {
		
		objeto.setCodigo(null);	
		for(AtributoMaterial a: objeto.getAtributoMateriais()) {
			atributoMaterialService.salvar(a);
		}
		return repository.save(objeto);
	}
	
	@Override
	public Material atualizar(Long codigo, Material objetoModificado) throws ServiceApplicationException{	
		Material objetoAtualizado = buscarPorCodigo(codigo);
		
		
//		List<AtributoMaterial> atributosModificados = objetoModificado.getAtributoMateriais();
//		List<AtributoMaterial> atributos = objetoAtualizado.getAtributoMateriais();
//		
//
//		for(int i = 0; i<atributos.size(); i++) {
//			AtributoMaterial a = atributoMaterialService.buscarPorCodigo(new Long(atributos.get(i).getCodigo()));
//			atributos.set(i, atributoMaterialService.atualizar(a.getCodigo(), atributosModificados.get(i)));
//				
//		}
//
//		if(atributosModificados.size() != atributos.size()) {
//			for(int i = atributos.size(); i<atributosModificados.size();i++) {
//				AtributoMaterial salvo = null;
//				try {
//					salvo = atributoMaterialService.salvar(atributosModificados.get(i));
//				} catch (ServiceApplicationException e) {
//
//				}
//				atributos.add(salvo);
//			}
//		}
//		
//		objetoModificado.setAtributoMateriais(atributos);
		for(AtributoMaterial a : objetoAtualizado.getAtributoMateriais()) {
			atributoMaterialService.deletar(a.getCodigo());
		}
		objetoAtualizado.setAtributoMateriais(null);
		
		List<AtributoMaterial> atributos = new ArrayList<AtributoMaterial>();
		for(AtributoMaterial a : objetoModificado.getAtributoMateriais()) {
			atributos.add(atributoMaterialService.salvar(a));

		}
		
		objetoModificado.setAtributoMateriais(atributos);
		
		
		BeanUtils.copyProperties(objetoModificado, objetoAtualizado, "codigo");
		return repository.save(objetoAtualizado);
	}
	
	
	public List<Material> filtrar(MaterialFilter filter){
		
		return repository.filtrar(filter);
		
	}
}
