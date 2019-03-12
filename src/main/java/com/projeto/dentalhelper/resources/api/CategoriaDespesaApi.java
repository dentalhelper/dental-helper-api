package com.projeto.dentalhelper.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.dentalhelper.domains.CategoriaDespesa;

@RequestMapping(value = "/categorias-despesa")
public interface CategoriaDespesaApi {

	@PostMapping(value = "/novo")
	public ResponseEntity<CategoriaDespesa> post(@Valid @RequestBody CategoriaDespesa objeto,
			HttpServletResponse response);

	@GetMapping
	public List<CategoriaDespesa> getAll();
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDespesa> getByCodigo(@PathVariable Long codigo);

	@PutMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDespesa> put(@PathVariable Long codigo,
			@Valid @RequestBody CategoriaDespesa objeto);
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);
}
