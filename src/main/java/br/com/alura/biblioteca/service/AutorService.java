package br.com.alura.biblioteca.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.biblioteca.dto.AutorDto;
import br.com.alura.biblioteca.dto.AutorFormDto;
import br.com.alura.biblioteca.modelo.Autor;
import br.com.alura.biblioteca.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorRepository autorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public Page<AutorDto> listar(Pageable paginacao) {
		
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores.map(a -> modelMapper.map(a, AutorDto.class));
		
	}

	@Transactional
	public AutorDto cadastrar(AutorFormDto dto) {
		
		Autor autor = modelMapper.map(dto, Autor.class);
		autorRepository.save(autor);
		return modelMapper.map(autor, AutorDto.class);
		
	}
}
