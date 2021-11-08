package br.com.alura.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.biblioteca.modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
