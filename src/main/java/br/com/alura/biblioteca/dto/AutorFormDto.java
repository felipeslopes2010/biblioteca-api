package br.com.alura.biblioteca.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDto {

	@NotBlank
	@Size(min = 1, max = 30)
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;
	
	@NotBlank
	private String curriculo;
	
}
