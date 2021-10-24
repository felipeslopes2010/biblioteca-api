package br.com.alura.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.biblioteca.dto.ItemLivrariaDto;
import br.com.alura.biblioteca.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	@Query("SELECT new br.com.alura.biblioteca.dto.ItemLivrariaDto( "
			+ "a.nome, "
			+ "COUNT(*), "
			+ "COUNT(*) * 1.0 / (SELECT COUNT(*) FROM Livro l2) * 1.0 as percentual) "
			+ "FROM Livro l "
			+ "JOIN Autor a ON l.autor.id = a.id "
			+ "GROUP BY a.nome ")
	List<ItemLivrariaDto> relatorioBiblioteca();
}