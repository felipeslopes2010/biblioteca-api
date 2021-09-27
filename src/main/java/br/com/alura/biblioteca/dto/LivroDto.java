package br.com.alura.biblioteca.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;

import br.com.alura.biblioteca.modelo.Autor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {

	String titulo;
	LocalDate dataLancamento;
	Integer numeroPaginas;
	Autor autor;
	
	@JsonGetter("autor")
	public String getNomeAutor() {
		return autor.getNome();
	}
}
