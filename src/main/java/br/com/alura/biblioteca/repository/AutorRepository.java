package br.com.alura.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.biblioteca.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
