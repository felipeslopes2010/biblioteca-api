package br.com.alura.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDetalhadoDto extends LivroDto {

	private AutorDto autor;
	
	@JsonGetter("autor")
	private String getNomeAutor() {
		
		return autor.getNome();
		
	}
	
}
