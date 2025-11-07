package br.com.TrueUnion.TrueUnion.Utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.TrueUnion.TrueUnion.Entities.Evento;

public class Utils {

	/*
	 * CLASSE VOLTADO PARA FERRAMENTAS EXTRAS DE VALIDAÇÃO NOS SCRIPTS.
	 */

	public static boolean validadorDeLocalDate(LocalDate data) {
		Long teste = ChronoUnit.DAYS.between(data, LocalDate.now());

		if (teste > 0) {
			System.out.println(teste);
			throw new IllegalArgumentException("A data esta anterior ao dia de HOJE");
		} else {
			return true;
		}
	}

	public static boolean cpfValido(String cpf) {
		int counter = 0;
		for (char caractere : cpf.toCharArray()) {
			if (Character.isAlphabetic(caractere)) {
				throw new IllegalArgumentException("Error, o cpf nao pode conter letra");
			} else {
				counter++;
			}

		}
		if (counter < 11 || counter > 11) {
			throw new IllegalArgumentException("Erro, cpf menor ou maior que 11 digitos");
		}
		return true;
	}

	public static Evento encontrarEventoComBaseNoNome(String nome, Iterable<Evento> eventos) {
		for (Evento evento : eventos) {
			if (nome.equalsIgnoreCase(evento.getNome())) {
				System.out.println("testesteste se foi encontrado o evento");
				return evento;

			}
		}
		throw new RuntimeException("Não foi encontrado nenhum evento com esse nome.");
	}
	
	

}
