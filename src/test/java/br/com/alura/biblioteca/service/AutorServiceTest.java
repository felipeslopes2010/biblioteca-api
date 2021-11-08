package br.com.alura.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.biblioteca.dto.AutorDto;
import br.com.alura.biblioteca.dto.AutorFormDto;
import br.com.alura.biblioteca.modelo.Usuario;
import br.com.alura.biblioteca.repository.AutorRepository;
import br.com.alura.biblioteca.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;
	
	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private AutorService autorService;

	private Usuario logado;

	@BeforeEach
	public void Before() {
		this.logado = new Usuario("J. R. R. Tolkien", "tolkien@email.com", "123456");
	}

	private AutorFormDto criarAutorFormDto() {
		AutorFormDto dto = new AutorFormDto("J. R. R. Tolkien",
				"tolkien@email.com.br",
				LocalDate.of(1982, 03, 01),
				"Autor do livro Senhor dos Aneis");

		return dto;

	}

	@Test
	void deveriaCadastrarUmAutor() {
		AutorFormDto formDto = criarAutorFormDto();
		
		AutorDto dto = autorService.cadastrar(formDto);

		Mockito.verify(autorRepository).save(Mockito.any());

		assertEquals(formDto.getNome(), dto.getNome());
		assertEquals(formDto.getEmail(), dto.getEmail());
		assertEquals(formDto.getDataNascimento(), dto.getDataNascimento());
		assertEquals(formDto.getCurriculo(), dto.getCurriculo());

	}

}