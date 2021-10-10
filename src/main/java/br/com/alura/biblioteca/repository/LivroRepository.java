package br.com.alura.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.biblioteca.dto.ItemLivrariaDto;
import br.com.alura.biblioteca.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	@Query("select new br.com.alura.biblioteca.dto.ItemLivrariaDto( "
			+ "l.autor.nome, "
			+ "count(*), "
			+ "count(*) * 1.0 / (select count(*) from Livro l2) * 1.0 as percentual) "
			+ "from Livro l "
			+ "group by l.autor.nome "
			+ "order by percentual desc")
	List<ItemLivrariaDto> relatorioBiblioteca();
}
