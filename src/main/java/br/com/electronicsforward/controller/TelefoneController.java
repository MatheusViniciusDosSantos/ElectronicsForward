package br.com.electronicsforward.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.electronicsforward.domain.Telefone;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.TelefoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api")
@Tag(name = "telefone", description = "API de telefones")
public class TelefoneController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int ROW_PER_PAGE = 5;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Operation(summary = "Busca telefones", description = "Buscar todos os telefones, buscar telefones por id do funcionário", tags = {"telefone"})
	@GetMapping(value = "/telefone", consumes = 
			MediaType.APPLICATION_JSON_VALUE, produces = 
				MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Telefone>> findAll(
			@Parameter(description = "Id do funcionário para pesquisa", allowEmptyValue = true)
			@RequestBody(required=false) long id,
			@Parameter(description = "Paginação", example = "{\"page\":0,\"size\":1}", allowEmptyValue = true)
			 Pageable pageable)	{
		if(StringUtils.isEmpty(id)) {
			return ResponseEntity.ok(telefoneService.findAll(pageable));
		} else {
			return ResponseEntity.ok(telefoneService.findAllByIdFuncionario(id, pageable));
		}
	}
	
	@Operation(summary = "Busca ID", description = "Buscar telefone por ID", tags = {"telefone"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso",
					content = @Content(schema = @Schema(implementation = Telefone.class))),
			@ApiResponse(responseCode = "404", description = "Telefone não encontrado")
	})
	@GetMapping(value = "/telefone/{id}", produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Telefone> findtelefoneById(@PathVariable long id) {
		try {
			Telefone telefone = telefoneService.findById(id);
			return ResponseEntity.ok(telefone);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	
	}
	
	@Operation(summary = "Adicionar telefone", description = "Adicionar novo telefone informado no banco de dados", tags = {"telefone"})
	@PostMapping(value = "/telefone")
	public ResponseEntity<Telefone> addTelefone(@RequestBody Telefone telefone) throws URISyntaxException {
		try {
			Telefone novoTelefone = telefoneService.save(telefone);
			return ResponseEntity.created(new URI("/api/telefone" + novoTelefone.getId())).body(telefone);
		} catch (ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@Operation(summary = "Alterar telefone", description = "Alterar valores do telefone com id selecionado", tags = {"telefone"})
	@PutMapping(value = "/telefone/{id}")
	public ResponseEntity<Telefone> updateTelefone(@Valid @RequestBody Telefone telefone,
			@PathVariable long id) {
		try {
			telefone.setId(id);
			telefoneService.update(telefone);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@Operation(summary = "Deletar telefone", description = "Deletar telefone com o ID informado", tags = {"telefone"})
	@DeleteMapping(path = "/telefone/{id}")
	public ResponseEntity<Void> deleteTelefoneById(@PathVariable long id) {
		try {
			telefoneService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

}
