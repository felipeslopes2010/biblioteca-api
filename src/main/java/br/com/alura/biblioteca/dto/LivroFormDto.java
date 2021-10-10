package br.com.alura.biblioteca.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDto {

	@NotBlank
	@Size(min = 10)
	private String titulo;
	
	@NotNull
	@PastOrPresent
	private LocalDate dataLancamento;
	
	@NotNull
	@Min(100)
	private Integer numeroPaginas;
	
	@JsonAlias("autor_id")
	private Long autorId;
}
