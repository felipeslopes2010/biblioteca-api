package br.com.alura.biblioteca.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDto {

	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	
}
