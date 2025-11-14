package br.com.TrueUnion.TrueUnion.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.Entities.ConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryConvidadosEmEventos;

import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.DashboardFinanceiroDeUmEvento;

@Service
public class DashboardService {
	@Autowired
	RepositoryConvidadosEmEventos repoEvento;

	@Autowired
	RepositoryEvento evento;

	public DashboardFinanceiroDeUmEvento mostrarDashboardComBaseNoEvento(int idEvento, Usuario usuario) {
		Evento evento = this.evento.findById(idEvento)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		List<ConvidadosEmEventos> eventosComUsuarioPendente = this.repoEvento.findQuantidadeDeLinhasPendentes(evento);
		List<ConvidadosEmEventos> eventosComUsuarioRecusadas = this.repoEvento.findQuantidadeDeLinhasRecusadas(evento);
		List<ConvidadosEmEventos> eventosComUsuarioAceitas = this.repoEvento.findQuantidadeDeLinhasAceitas(evento);

		return new DashboardFinanceiroDeUmEvento(evento.getId(), evento.getNome(), evento.getDonoEvento().getNome(),
				evento.getTotalDeDespesasAtuais(), evento.getOrcamentoEvento(), eventosComUsuarioAceitas.size(),
				eventosComUsuarioRecusadas.size(), eventosComUsuarioPendente.size());
	}

}
