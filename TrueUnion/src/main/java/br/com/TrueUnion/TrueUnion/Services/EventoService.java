package br.com.TrueUnion.TrueUnion.Services;

import java.lang.StackWalker.Option;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.Controller.UsuarioController;
import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.EventoCreateRequest;
import br.com.TrueUnion.TrueUnion.Entities.ConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.StatusRSVP;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryStatusRSVP;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.ConvidadosEmEventosResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.EventoResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;

@Service
public class EventoService {
	@Autowired
	RepositoryEvento event;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RepositoryUsuario usuarioRepository;
	@Autowired
	LoginService login;
	@Autowired
	RepositoryLoginSessao loginRepository;
	@Autowired
	RepositoryStatusRSVP statusRepository;
	@Autowired
	RepositoryConvidadosEmEventos convidadosEmEventos;

	@Transactional
	public EventoResponse createEvento(Usuario usuarioLogado, EventoCreateRequest evento) {
		Iterable<Evento> eventosVinculadosAoUsuario = this.event.findEventoByDono(usuarioLogado);

		if (usuarioLogado.getPerfilDoUsuario().getId() != 3) {
			if (evento.getDataFim().isBefore(evento.getDataInicio())) {
				throw new IllegalArgumentException("A data de fim nao pode ser anterior a data de inicio");
			} else {
				Evento eventoEntity = new Evento(evento.getNome(), evento.getOrcamento(), evento.getLocal(),
						evento.getDataInicio(), evento.getDataFim(), evento.getDescricao());

				eventoEntity.setDonoEvento(usuarioLogado);
				eventoEntity.setMotivoCancelamento(null);
				eventoEntity.setPassouOrcamento(false);

				this.event.save(eventoEntity);
				return new EventoResponse(eventoEntity.getId(), eventoEntity.getNome(),
						eventoEntity.getOrcamentoEvento(), eventoEntity.getLocal(), eventoEntity.getDataInicio(),
						eventoEntity.getDataFim(), eventoEntity.getDescricao());

			}

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@Transactional
	public ConvidadosEmEventosResponse convidar(Usuario dono, String email, int idEvento) {

		Usuario usuarioPeloEmail = this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		Evento eventoFind = this.event.findById(idEvento)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// VALIDAR SE O USUARIO JA FOI CONVIDADO PARA ESSE EVENTO

		ConvidadosEmEventos linhaDaTableConvidados = this.convidadosEmEventos.puxarLinhaConvidadosEmEventos(eventoFind,
				usuarioPeloEmail);

		if (eventoFind.getDonoEvento() == dono) {
			if (linhaDaTableConvidados != null) {
				throw new ResponseStatusException(HttpStatus.CONFLICT);
			} else {
				if (!usuarioPeloEmail.isInativo()) {
					ConvidadosEmEventos convidar = new ConvidadosEmEventos(usuarioPeloEmail, eventoFind);
					convidar.setStatus(this.statusRepository.findById(1)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

					this.convidadosEmEventos.save(convidar);
					StatusRSVP status = this.statusRepository.findById(1)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
					return new ConvidadosEmEventosResponse(eventoFind.getNome(), usuarioPeloEmail.getNome(),
							status.getDescProgramador());
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
				}

			}
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

	}

	@Transactional
	public void updateEvento(int idEvento, EventoCreateRequest eventoAlterado) {

		Evento e = this.event.findById(idEvento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (eventoAlterado.getNome() != null && !eventoAlterado.getNome().isBlank()) {
			e.setNome(eventoAlterado.getNome());

		}
		if (eventoAlterado.getOrcamento() != null) {
			e.setOrcamentoEvento(eventoAlterado.getOrcamento());
		}
		if (eventoAlterado.getLocal() != null && !eventoAlterado.getLocal().isBlank()) {
			e.setLocal(eventoAlterado.getLocal());
		}
		if (eventoAlterado.getDataInicio() != null && eventoAlterado.getDataFim() != null) {
			if (eventoAlterado.getDataInicio().isBefore(eventoAlterado.getDataFim())) {
				e.setDataInicio(eventoAlterado.getDataInicio());
				e.setDataFim(eventoAlterado.getDataFim());
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
			}
		} else if (eventoAlterado.getDataInicio() != null && eventoAlterado.getDataFim() == null) {
			if (eventoAlterado.getDataInicio().isBefore(e.getDataFim())) {
				e.setDataInicio(eventoAlterado.getDataInicio());
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
			}
		} else if (eventoAlterado.getDataFim() != null && eventoAlterado.getDataInicio() == null) {
			if (eventoAlterado.getDataFim().isAfter(e.getDataInicio())) {
				e.setDataFim(eventoAlterado.getDataFim());
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		this.event.save(e);

	}

	public Iterable<Evento> listarEventosVinculadoAoTokenDaPagina(Usuario usuario) {

		Iterable<Evento> eventosVinculadoAoUsuario = this.event.findEventoByDono(usuario);

		if (eventosVinculadoAoUsuario == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}
		return eventosVinculadoAoUsuario;
	}

	public List<EventoResponse> validarConvitesEnviadosParaMim(Usuario usuario) {

		Iterable<Evento> listaDeEventosQueOUsuarioEstaConvidado = this.convidadosEmEventos
				.eventosDisponiveisParaVerificar(usuario);
		List<EventoResponse> listaDeEventosParaJogarNaTela = new ArrayList<>();
		for (Evento evento : listaDeEventosQueOUsuarioEstaConvidado) {
			EventoResponse respostaEmTela = new EventoResponse(evento.getId(), evento.getNome(),
					evento.getOrcamentoEvento(), evento.getLocal(), evento.getDataInicio(), evento.getDataFim(),
					evento.getDescricao());
			listaDeEventosParaJogarNaTela.add(respostaEmTela);
		}
		return listaDeEventosParaJogarNaTela;
	}

	@Transactional
	public void responderConvitePendente(Usuario usuario, int idEvento, int idStatusRsvp) {
		Iterable<Evento> eventosDisponiveis = this.convidadosEmEventos.eventosDisponiveisParaVerificar(usuario);
		for (Evento e : eventosDisponiveis) {
			if (e.getId() == idEvento) {
				ConvidadosEmEventos linhaDeConvidados = this.convidadosEmEventos.puxarLinhaConvidadosEmEventos(e,
						usuario);
				linhaDeConvidados.setStatus(this.statusRepository.findById(idStatusRsvp)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT)));
				this.convidadosEmEventos.save(linhaDeConvidados);
				break;
			}
		}

	}

	@Transactional
	public EventoResponse cancelarEvento(String senha, Usuario usuario, int idEvento, String motivoCancelamento) {

		Evento e = this.event.findById(idEvento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (!e.isConcluido() && !e.getDataInicio().isBefore(LocalDate.now()) && motivoCancelamento != null) {
			if (senha.equals(usuario.getSenha())) {
				e.setCancelada(true);
				e.setMotivoCancelamento(motivoCancelamento);
				this.event.save(e);
				return new EventoResponse(e.getId(), e.getNome(), e.getOrcamentoEvento(), e.getLocal(),
						e.getDataInicio(), e.getDataFim(), e.getDescricao());

			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public List<EventoResponse> buscarEventoPeloNome(Usuario usuario, String nomeEvento) {

		Iterable<Evento> eventosVinculadosAoUsuario = this.event.findEventoByDono(usuario);
		List<EventoResponse> eventosParaMostrarEmTela = new ArrayList<>();

		for (Evento e : eventosVinculadosAoUsuario) {
			if (e.getNome().equalsIgnoreCase(nomeEvento)) {
				eventosParaMostrarEmTela.add(new EventoResponse(e.getId(), e.getNome(), e.getOrcamentoEvento(),
						e.getLocal(), e.getDataInicio(), e.getDataFim(), e.getDescricao()));
			}
		}
		return eventosParaMostrarEmTela;

	}

}
