package br.com.alura.biblioteca.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.biblioteca.dto.AtualizacaoLivroFormDto;
import br.com.alura.biblioteca.dto.LivroDetalhadoDto;
import br.com.alura.biblioteca.dto.LivroDto;
import br.com.alura.biblioteca.dto.LivroFormDto;
import br.com.alura.biblioteca.modelo.Autor;
import br.com.alura.biblioteca.modelo.Livro;
import br.com.alura.biblioteca.repository.AutorRepository;
import br.com.alura.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;

	@Autowired
	AutorRepository autorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public Page<LivroDto> listar(Pageable paginacao) {

		Page<Livro> livros = livroRepository.findAll(paginacao);
		
		return livros
				.map(l -> modelMapper.map(l, LivroDto.class));

	}

	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {
		Long idAutor = dto.getAutorId();

		try {
			Autor autor = autorRepository.getById(idAutor);
			Livro livro = modelMapper.map(dto, Livro.class);
			livro.setId(null);
			livro.setAutor(autor);

			livroRepository.save(livro);

			return modelMapper.map(livro, LivroDto.class);

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Autor inexistente!");
		}
	}
	
	@Transactional
	public LivroDto atualizar(AtualizacaoLivroFormDto dto) {
		
		Livro livro = livroRepository.getById(dto.getId());
		
		livro.atualizarInformacoes(dto.getTitulo(), dto.getDataLancamento(), dto.getNumeroPaginas());
		
		return modelMapper.map(livro, LivroDto.class);
	}
	
	@Transactional
	public void remover(@NotNull Long id) {
		
		livroRepository.deleteById(id);

	}
	
	public LivroDetalhadoDto detalhar(@NotNull Long id) {
		
		Livro livro = livroRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(livro, LivroDetalhadoDto.class);
		
	}
	
}
