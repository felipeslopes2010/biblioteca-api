package br.com.alura.biblioteca.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.biblioteca.dto.ItemLivrariaDto;
import br.com.alura.biblioteca.modelo.Autor;
import br.com.alura.biblioteca.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	void deveriaRetornarRelatorioDeLivros() {
		
		Autor a1 = new Autor("André da Silva", "andre@email.com.br", LocalDate.now(), "autor");
		em.persist(a1);
		Autor a2 = new Autor("Fernanda Nogueira", "fernanda@email.com.br", LocalDate.now(), "autor");
		em.persist(a2);
		Autor a3 = new Autor("Juliana Carvalho", "juliana@email.com.br", LocalDate.now(), "autor");
		em.persist(a3);
		Autor a4 = new Autor("Rita de Asis", "rita@email.com.br", LocalDate.now(), "autor");
		em.persist(a4);
		Autor a5 = new Autor("Rodrigo de Souza", "rodrigo@email.com.br", LocalDate.now(), "autor");
		em.persist(a5);
		
		Livro l1 = new Livro("Aprenda Java em 21 dias",
				LocalDate.now(),
				100,
				a1);
		em.persist(l1);
		
		Livro l2 = new Livro("Como ser mais produtivo",
				LocalDate.now(),
				200,
				a2);
		em.persist(l2);
		
		Livro l3 = new Livro("Aprenda a falar em publico",
				LocalDate.now(),
				300,
				a3);
		em.persist(l3);
		
		Livro l4 = new Livro("Otimizando seu tempo",
				LocalDate.now(),
				200,
				a2);
		em.persist(l4);
		
		Livro l5 = new Livro("Como fazer bolos incriveis",
				LocalDate.now(),
				100,
				a4);
		em.persist(l5);
		
		Livro l6 = new Livro("Investindo em ações na bolsa de valores",
				LocalDate.now(),
				300,
				a5);
		em.persist(l6);
		
		Livro l7 = new Livro("Aprenda Python em 12 dias",
				LocalDate.now(),
				500,
				a1);
		em.persist(l7);
		
		List<ItemLivrariaDto> relatorio = repository.relatorioBiblioteca();
		
		Assertions
		.assertThat(relatorio)
		.hasSize(5)
		.extracting(ItemLivrariaDto::getAutor, ItemLivrariaDto::getQuantidadeLivros, ItemLivrariaDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("Fernanda Nogueira", 2L, new BigDecimal("28.57")),
				Assertions.tuple("André da Silva", 2L, new BigDecimal("28.57")),
				Assertions.tuple("Rita de Asis", 1L,new BigDecimal("14.29")),
				Assertions.tuple("Rodrigo de Souza", 1L,new BigDecimal("14.29")),
				Assertions.tuple("Juliana Carvalho", 1L, new BigDecimal("14.29"))
				);
				
	}

}
