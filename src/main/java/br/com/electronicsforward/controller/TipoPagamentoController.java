package br.com.electronicsforward.controller;

import br.com.electronicsforward.domain.TipoPagamento;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.TipoPagamentoService;
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
public class TipoPagamentoController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private TipoPagamentoService tipoPagamentoService;
	
	@GetMapping(value = "/tipoPagamento", consumes = 
			MediaType.APPLICATION_JSON_VALUE, produces = 
				MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<TipoPagamento>> findAll(
			@RequestBody(required=false) String tipo, Pageable pegeable) {
		if(StringUtils.isEmpty(tipo)) {
			return ResponseEntity.ok(tipoPagamentoService.findAll(pegeable));
		} else {
			return ResponseEntity.ok(tipoPagamentoService.findAllByTipo(tipo, pegeable));
		}
	}
	
	@GetMapping(value = "/tipoPagamento/{id}", produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoPagamento> findTipoPagamentoById(@PathVariable long id) {
		try {
			TipoPagamento tipoPagamento = tipoPagamentoService.findById(id);
			return ResponseEntity.ok(tipoPagamento);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@PostMapping(value = "/tipoPagamento")
	public ResponseEntity<TipoPagamento> addTipoPagamento(@RequestBody TipoPagamento tipoPagamento) throws URISyntaxException {
		try {
			TipoPagamento novoTipoPagamento = tipoPagamentoService.save(tipoPagamento);
			return ResponseEntity.created(new URI("/api/tipoPagamento" + novoTipoPagamento.getId())).body(tipoPagamento);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(value = "/tipoPagamento/{id}")
	public ResponseEntity<TipoPagamento> updateTipoPagamento(@RequestBody TipoPagamento tipoPagamento,
			@PathVariable long id) {
		try {
			tipoPagamento.setId(id);
			tipoPagamentoService.update(tipoPagamento);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping(path = "/tipoPagamento/{id}")
	public ResponseEntity<Void> deleteTipoPagamentoById(@PathVariable long id) {
		try {
			tipoPagamentoService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

}
