package br.com.electronicsforward.controller;

import br.com.electronicsforward.domain.Venda;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.VendaService;
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
public class VendaController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping(value = "/venda", consumes =
			MediaType.APPLICATION_JSON_VALUE, produces = 
				MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Venda>> findAll(
			@RequestBody(required=false) String tipo, Pageable pegeable) {
		if(StringUtils.isEmpty(tipo)) {
			return ResponseEntity.ok(vendaService.findAll(pegeable));
		} else {
			return ResponseEntity.ok(vendaService.findAllByTipoPagamento(tipo, pegeable));
		}
	}
	
	@GetMapping(value = "/venda/{id}", produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Venda> findVendaById(@PathVariable long id) {
		try {
			Venda venda = vendaService.findById(id);
			return ResponseEntity.ok(venda);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@PostMapping(value = "/venda")
	public ResponseEntity<Venda> addVenda(@RequestBody Venda venda) throws URISyntaxException {
		try {
			Venda novoVenda = vendaService.save(venda);
			return ResponseEntity.created(new URI("/api/venda" + novoVenda.getId())).body(venda);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/venda/{id}")
	public ResponseEntity<Venda> updateVenda(@RequestBody Venda venda,
			@PathVariable long id) {
		try {
			venda.setId(id);
			vendaService.update(venda);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping(path = "/venda/{id}")
	public ResponseEntity<Void> deleteVendaById(@PathVariable long id) {
		try {
			vendaService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

}
