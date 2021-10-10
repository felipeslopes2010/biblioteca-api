package br.com.alura.biblioteca.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {

	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Integer numeroPaginas;
	private AutorDto autor;

	@JsonGetter("autor")
	private String getNomeAutor() {
		
		return autor.getNome();
		
	}
}
