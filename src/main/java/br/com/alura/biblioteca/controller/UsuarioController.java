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

import br.com.alura.biblioteca.dto.AtualizacaoUsuarioFormDto;
import br.com.alura.biblioteca.dto.UsuarioDto;
import br.com.alura.biblioteca.dto.UsuarioFormDto;
import br.com.alura.biblioteca.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuário")
public class UsuarioController {
		
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	@ApiOperation("Listar usuarios")
	public Page<UsuarioDto>listar(@PageableDefault(size=10)Pageable paginacao){
		return service.listar(paginacao);		
	}
	
	@PostMapping
	@ApiOperation("Cadastrar novo usuario")
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto dto,
			UriComponentsBuilder uriBuilder) {
		UsuarioDto usuarioDto = service.cadastrar(dto);
		
		URI uri = uriBuilder
				.path("/usuarios/{id}")
				.buildAndExpand(usuarioDto.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(usuarioDto);
	}
	
	@PutMapping
	public ResponseEntity<UsuarioDto> atualizar(@RequestBody @Valid AtualizacaoUsuarioFormDto dto) {
		UsuarioDto atualizada = service.atualizar(dto);		
		return ResponseEntity.ok(atualizada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDto> remover(@PathVariable @NotNull Long id) {
		service.remover(id);		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable @NotNull Long id) {
		UsuarioDto dto = service.detalhar(id);		
		return ResponseEntity.ok(dto);
	}
}