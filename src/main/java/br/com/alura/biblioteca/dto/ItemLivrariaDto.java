package br.com.alura.biblioteca.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;

@Getter
public class ItemLivrariaDto {

	private String autor;
	private Long quantidadeLivros;
	private BigDecimal percentual;
	
	public ItemLivrariaDto(String autor, Long quantidadeLivros, Double quantidadeLivrosTotal) {
		this.autor = autor;
		this.quantidadeLivros = quantidadeLivros;	
		this.percentual = new BigDecimal(quantidadeLivrosTotal)
				.multiply(new BigDecimal(100))
				.setScale(2, RoundingMode.HALF_UP);
	}

}
