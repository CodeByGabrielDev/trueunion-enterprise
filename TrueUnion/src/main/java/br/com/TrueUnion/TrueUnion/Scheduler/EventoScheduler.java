package br.com.TrueUnion.TrueUnion.Scheduler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import jakarta.transaction.Transactional;

@Component
public class EventoScheduler {
	@Autowired
	RepositoryEvento evento;

	@Transactional
	@Scheduled(cron = "0 0/10 * * * ?")
	public void settarEventosConcluidos() {

		Iterable<Evento> eventosQueForamConcluidos = this.evento.findEventosConcluidos(LocalDate.now());

		for (Evento e : eventosQueForamConcluidos) {
			e.setConcluido(true);
			this.evento.save(e);
		}
	}

}
