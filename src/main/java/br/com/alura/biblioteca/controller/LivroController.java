package br.com.alura.biblioteca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.biblioteca.dto.LivroDto;
import br.com.alura.biblioteca.dto.LivroFormDto;
import br.com.alura.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroService service;
	
	@GetMapping
	public List<LivroDto> listar() {
		return service.listar();
	}
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid LivroFormDto dto) {
		service.cadastrar(dto);
	}
}
