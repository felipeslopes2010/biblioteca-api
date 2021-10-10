package br.com.alura.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.biblioteca.dto.ItemLivrariaDto;
import br.com.alura.biblioteca.repository.LivroRepository;

@Service
public class RelatorioService {

	@Autowired
	LivroRepository repository;
	
	public List<ItemLivrariaDto> relatorioBiblioteca(){
		
		return repository.relatorioBiblioteca();
		
	}
}
