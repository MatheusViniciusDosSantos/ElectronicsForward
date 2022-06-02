package br.com.electronicsforward.controller;

import br.com.electronicsforward.domain.Funcionario;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class FuncionarioController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping(value = "/funcionario", consumes = 
			MediaType.APPLICATION_JSON_VALUE, produces = 
				MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Funcionario>> findAll(
			@RequestBody(required=false) String nome, Pageable pegeable) {
		if(StringUtils.isEmpty(nome)) {
			return ResponseEntity.ok(funcionarioService.findAll(pegeable));
		} else {
			return ResponseEntity.ok(funcionarioService.findAllByNome(nome, pegeable));
		}
	}
	
	@GetMapping(value = "/funcionario/{id}", produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> findFuncionarioById(@PathVariable long id) {
		try {
			Funcionario funcionario = funcionarioService.findById(id);
			return ResponseEntity.ok(funcionario);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@PostMapping(value = "/funcionario")
	public ResponseEntity<Funcionario> addFuncionario(@RequestBody Funcionario funcionario) throws URISyntaxException {
		try {
			Funcionario novoFuncionario = funcionarioService.save(funcionario);
			return ResponseEntity.created(new URI("/api/funcionario" + novoFuncionario.getId())).body(funcionario);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/funcionario/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@RequestBody Funcionario funcionario,
			@PathVariable long id) {
		try {
			funcionario.setId(id);
			funcionarioService.update(funcionario);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping(path = "/funcionario/{id}")
	public ResponseEntity<Void> deleteFuncionarioById(@PathVariable long id) {
		try {
			funcionarioService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

}
