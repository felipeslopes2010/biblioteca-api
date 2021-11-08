package br.com.alura.biblioteca.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDto {

	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private String curriculo;
	
}
