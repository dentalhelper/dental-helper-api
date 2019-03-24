package com.projeto.dentalhelper.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dentalhelper.domains.Material;

@RequestMapping(value = "/materiais")
public interface MaterialApi {

	@PostMapping(value = "/novo")
	public ResponseEntity<Material> post(@Valid @RequestBody Material objeto,
			HttpServletResponse response);

	@GetMapping
	public List<Material> getAll();
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Material> getByCodigo(@PathVariable Long codigo);

	@PutMapping(value = "/{codigo}")
	public ResponseEntity <Material> put(@PathVariable Long codigo,@Valid @RequestBody Material objeto);
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
}
