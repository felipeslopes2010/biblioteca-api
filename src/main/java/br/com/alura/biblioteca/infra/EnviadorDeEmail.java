package br.com.alura.biblioteca.infra;

public interface EnviadorDeEmail {

	void enviarEmail(String destinatario, String assunto, String mensagem);

}