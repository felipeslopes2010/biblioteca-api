package br.com.alura.biblioteca.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String login;
	
	@NotNull
	private Long perfilId;

}
