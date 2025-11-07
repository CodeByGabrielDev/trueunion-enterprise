package br.com.TrueUnion.TrueUnion.Scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import jakarta.transaction.Transactional;

@Component
public class InativaContaParada {
	@Autowired
	RepositoryUsuario usuarioRepo;

	@Transactional
	@Scheduled(cron = "0 0/10 * * * ?")
	public void inativaContaParada() {

		List<Usuario> listaDeUsuarios = usuarioRepo.findUsuariosInativosPorTempo();

		for (Usuario u : listaDeUsuarios) {
			u.setInativo(true);
			usuarioRepo.save(u);
		}

	}

}
