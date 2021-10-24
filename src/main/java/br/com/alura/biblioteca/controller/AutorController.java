package br.com.alura.biblioteca.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.biblioteca.dto.AtualizacaoAutorFormDto;
import br.com.alura.biblioteca.dto.AutorDetalhadoDto;
import br.com.alura.biblioteca.dto.AutorDto;
import br.com.alura.biblioteca.dto.AutorFormDto;
import br.com.alura.biblioteca.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autores")
@Api(tags = "Autores")
public class AutorController {

	@Autowired
	AutorService service;
	
	@GetMapping
	@ApiOperation("Lista Autores")
	public Page<AutorDto> listar(@PageableDefault(size = 10) Pageable paginacao){
		
		return service.listar(paginacao);
		
	}
	
	@PostMapping
	@ApiOperation("Cadastra novo Autor")
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorFormDto dto,
			UriComponentsBuilder uriBuilder) {
		
		AutorDto autorDto = service.cadastrar(dto);

		URI uri = uriBuilder.path("autores/{id}").buildAndExpand(autorDto.getId()).toUri();
		return ResponseEntity.created(uri).body(autorDto);
		
	}
	
	@PutMapping
	@ApiOperation("Atualiza Autor")
	public ResponseEntity<AutorDto> atualizar(@RequestBody @Valid AtualizacaoAutorFormDto dto) {
		
		AutorDto autorAtualizado = service.atualizar(dto);
		
		return ResponseEntity.ok(autorAtualizado);
	}
	
	@DeleteMapping({"/{id}"})
	@ApiOperation("Remove Autor")
	public ResponseEntity<AutorDto> remover(@PathVariable @NotNull Long id) {
		service.remover(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Detalha Transacao")
	public ResponseEntity<AutorDetalhadoDto> detalhar(@PathVariable @NotNull Long id) {
		
		AutorDetalhadoDto dto = service.detalhar(id);
		
		return ResponseEntity.ok(dto);
	}
}

