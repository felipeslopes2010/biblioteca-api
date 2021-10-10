package br.com.alura.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemLivrariaDto {

	private String autor;
	private Long quantidadeLivros;
	private Double percentual;

}
