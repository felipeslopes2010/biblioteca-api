package br.com.alura.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.biblioteca.dto.LivroDto;
import br.com.alura.biblioteca.dto.LivroFormDto;
import br.com.alura.biblioteca.repository.AutorRepository;
import br.com.alura.biblioteca.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

	@Mock
	private LivroRepository repository;
	
	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private LivroService service;
	
	private LivroFormDto criarLivroFormDto() {
		LivroFormDto formDto = new LivroFormDto(
				"Aprenda Java em 21 dias",
				LocalDate.now(),
				100,
				1l);
		return formDto;				
	}
	@Test
	void deveriaCadastrarUmLivro() {
		LivroFormDto formDto = criarLivroFormDto();
		
		LivroDto dto = service.cadastrar(formDto);
		
		Mockito.verify(repository).save(Mockito.any());
		
		assertEquals(formDto.getTitulo(), dto.getTitulo());
		assertEquals(formDto.getDataLancamento(), dto.getDataLancamento());
		assertEquals(formDto.getNumeroPaginas(), dto.getNumeroPaginas());
	}
	
	@Test
	void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {
		
		LivroFormDto formDto = criarLivroFormDto();
		
		Mockito
		.when(autorRepository.getById(formDto.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
	
		
		assertThrows(IllegalArgumentException.class, () -> service.cadastrar(formDto));
		
	}
}
