package br.com.alura.biblioteca.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.biblioteca.dto.LivroDto;
import br.com.alura.biblioteca.dto.LivroFormDto;
import br.com.alura.biblioteca.modelo.Livro;
import br.com.alura.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivroDto> listar(Pageable paginacao) {
		
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(l -> modelMapper.map(l, LivroDto.class));
		
	}
	
	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {
		
		Livro livro = modelMapper.map(dto, Livro.class);
		livro.setId(null);
		
		livroRepository.save(livro);
		return modelMapper.map(livro, LivroDto.class);
		
	}
}
