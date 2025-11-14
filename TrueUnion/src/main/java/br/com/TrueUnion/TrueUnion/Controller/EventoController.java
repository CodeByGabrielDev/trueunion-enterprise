package br.com.TrueUnion.TrueUnion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.EventoCreateRequest;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.ConvidadosEmEventosResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.DashboardFinanceiroDeUmEvento;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.EventoResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.RelatorioResponse;
import br.com.TrueUnion.TrueUnion.Services.DashboardService;
import br.com.TrueUnion.TrueUnion.Services.EventoService;
import br.com.TrueUnion.TrueUnion.Services.LoginService;
import br.com.TrueUnion.TrueUnion.Services.RelatorioService;

@RestController
@RequestMapping("/api-trueunion/events")
public class EventoController {
	@Autowired
	EventoService eventoService;
	@Autowired
	LoginService loginService;
	@Autowired
	RelatorioService relatorio;
	@Autowired
	DashboardService dashboard;

	@PostMapping
	public ResponseEntity<EventoResponse> criarEvento(@RequestHeader("Authorization") String header,
			@RequestBody EventoCreateRequest eventoRequest) {
		// Extrai o token do header
		String token = header.replace("Bearer ", "").trim();

		// Busca o usuário dono do token
		Usuario usuarioLogado = loginService.getUsuarioByToken(token);

		// Chama o service passando o usuário logado
		EventoResponse response = eventoService.createEvento(usuarioLogado, eventoRequest);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/{idEvento}/invite")
	public ConvidadosEmEventosResponse convidarUsuariosParaEvento(@RequestHeader("Authorization") String header,
			@RequestParam String email, @PathVariable int idEvento) {

		String tokenUsuario = header.replace("Bearer ", "").trim();

		Usuario usuarioDaSessaoAtual = this.loginService.getUsuarioByToken(tokenUsuario);

		return this.eventoService.convidar(usuarioDaSessaoAtual, email, idEvento);

	}

	@GetMapping("/invites")
	public List<EventoResponse> convitesRecebidos(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);
		return this.eventoService.validarConvitesEnviadosParaMim(usuarioPegoPeloToken);
	}

	@PutMapping("/invites/{idEvento}/")
	public void responderConvite(@RequestHeader("Authorization") String header, @PathVariable int idEvento,
			@RequestParam int idStatusRsvp) {
		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);
		this.eventoService.responderConvitePendente(usuarioPegoPeloToken, idEvento, idStatusRsvp);
	}

	@PutMapping("/{IdEvento}")
	public void atualizarEvento(@RequestHeader("Authorization") String header, @RequestBody EventoCreateRequest evento,
			@PathVariable int IdEvento) {
		String token = header.replace("Bearer ", "").trim();
		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);

		this.eventoService.updateEvento(IdEvento, evento);
	}

	@PutMapping("/{idEvento}/cancellation")
	public EventoResponse cancelarEvento(@RequestHeader("Authorization") String header, @RequestParam String senha,
			@PathVariable int idEvento, @RequestParam String motivoCancelamento) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuario = this.loginService.getUsuarioByToken(token);

		return this.eventoService.cancelarEvento(senha, usuario, idEvento, motivoCancelamento);

	}

	@GetMapping
	public List<EventoResponse> buscarEventoPeloNome(@RequestHeader String header, String nomeEvento) {
		String token = header.replace("Bearer ", "").trim();

		Usuario usuario = this.loginService.getUsuarioByToken(token);

		return this.eventoService.buscarEventoPeloNome(usuario, nomeEvento);
	}

	@GetMapping("/{idEvento}/reports")
	public List<RelatorioResponse> puxarRelatorios(@RequestHeader("Authorization") String header,
			@PathVariable int idEvento) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);

		return this.relatorio.tirarRelatorio(usuarioPegoPeloToken, idEvento);
	}

	@GetMapping("/{idEvento}/dashboard")
	public DashboardFinanceiroDeUmEvento dashboardRelacionadoAoEvento(@RequestHeader("Authorization") String header,
			@PathVariable int idEvento) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);
		return dashboard.mostrarDashboardComBaseNoEvento(idEvento, usuarioPegoPeloToken);

	}
}
