package br.com.alura.biblioteca.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.biblioteca.dto.AtualizacaoAutorFormDto;
import br.com.alura.biblioteca.dto.AutorDetalhadoDto;
import br.com.alura.biblioteca.dto.AutorDto;
import br.com.alura.biblioteca.dto.AutorFormDto;
import br.com.alura.biblioteca.modelo.Autor;
import br.com.alura.biblioteca.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	public Page<AutorDto> listar(Pageable paginacao) {
		
		return repository
				.findAll(paginacao)
				.map(a -> modelMapper.map(a, AutorDto.class));
		
	}

	@Transactional
	public AutorDto cadastrar(AutorFormDto dto) {
		
		Autor autor = modelMapper.map(dto, Autor.class);
		repository.save(autor);
		return modelMapper.map(autor, AutorDto.class);
		
	}
	
	@Transactional
	public AutorDto atualizar(AtualizacaoAutorFormDto dto) {
		Autor autor = repository.getById(dto.getId());
		
		autor.atualizarInformacoes(dto.getNome(), dto.getEmail(), dto.getDataNascimento(), dto.getCurriculo());
		
		return modelMapper.map(autor, AutorDto.class);
	}

	@Transactional
	public void remover(Long id) {
		
		repository.deleteById(id);
		
	}

	public AutorDetalhadoDto detalhar(@NotNull Long id) {

		Autor autor = repository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(autor, AutorDetalhadoDto.class);
		
	}
}
