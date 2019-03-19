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

import com.projeto.dentalhelper.domains.Despesa;
import com.projeto.dentalhelper.repositories.filter.DespesaFilter;

@RequestMapping(value = "despesa")
public interface DespesaApi {
	@PostMapping(value = "/novo")
	public ResponseEntity<Despesa> post(@Valid @RequestBody Despesa objeto,
			HttpServletResponse response);

	@GetMapping
	public List<Despesa> getByFilter(DespesaFilter filter);
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Despesa> getByCodigo(@PathVariable Long codigo);

	@PutMapping(value = "/{codigo}")
	public ResponseEntity <Despesa> put(@PathVariable Long codigo,
			@Valid @RequestBody Despesa objeto);
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo);

}
