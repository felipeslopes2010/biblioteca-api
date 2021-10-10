package br.com.alura.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.biblioteca.dto.ItemLivrariaDto;
import br.com.alura.biblioteca.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	RelatorioService service;

	@GetMapping("/livraria")
	public List<ItemLivrariaDto> relatorioBiblioteca() {

		return service.relatorioBiblioteca();

	}
}
