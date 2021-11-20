package br.com.alura.biblioteca.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Profile({ "default", "test" })
public class EnviadorDeEmailFake implements EnviadorDeEmail {

	@Async
	public void enviarEmail(String destinatario, String assunto, String mensagem) {

		System.out.println("ENVIANDO EMAIL:");
		System.out.println("Destinatario: " + destinatario);
		System.out.println("Assunto: " + assunto);
		System.out.println("Mensagem: " + mensagem);
	}

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
}
