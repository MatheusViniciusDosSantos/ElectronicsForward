package br.com.electronicsforward.controller;

import br.com.electronicsforward.domain.Cliente;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.ClienteService;
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
public class ClienteController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/cliente", consumes = 
			MediaType.APPLICATION_JSON_VALUE, produces = 
				MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Cliente>> findAll(
			@RequestBody(required=false) String nome, Pageable pegeable) {
		if(StringUtils.isEmpty(nome)) {
			return ResponseEntity.ok(clienteService.findAll(pegeable));
		} else {
			return ResponseEntity.ok(clienteService.findAllByNome(nome, pegeable));
		}
	}
	
	@GetMapping(value = "/cliente/{id}", produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> findClienteById(@PathVariable long id) {
		try {
			Cliente cliente = clienteService.findById(id);
			return ResponseEntity.ok(cliente);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@PostMapping(value = "/cliente")
	public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) throws URISyntaxException {
		try {
			Cliente novoCliente = clienteService.save(cliente);
			return ResponseEntity.created(new URI("/api/cliente" + novoCliente.getId())).body(cliente);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/cliente/{id}")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente,
			@PathVariable long id) {
		try {
			cliente.setId(id);
			clienteService.update(cliente);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping(path = "/cliente/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable long id) {
		try {
			clienteService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

}
