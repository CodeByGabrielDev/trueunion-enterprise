package br.com.TrueUnion.TrueUnion.Controller;

import java.util.List;

import java.util.Optional;

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

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.UsuarioRequest;
import br.com.TrueUnion.TrueUnion.Entities.LoginSessao;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Services.UsuarioService;

@RestController
@RequestMapping("/api-trueunion/users")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RepositoryLoginSessao loginRepository;

	@GetMapping("/{id}")
	public UsuarioResponseGeneric buscarUsuarioPorId(@PathVariable("id") int id) {
		return this.usuarioService.buscarUsuarioPorId(id);
	}

	@GetMapping
	public List<UsuarioResponseGeneric> buscarTodosUsuariosDisponiveis() {
		return this.usuarioService.listarUsuariosDisponiveis();
	}

	@PutMapping("/password/reset")
	public ResponseEntity<UsuarioResponseGeneric> resetPassword(@RequestHeader("Authorization") String header,
			@RequestParam String senha, @RequestParam String confirmaSenha) {
		String tokenRetiradoDoHeader = header.replace("Bearer ", "").trim();

		LoginSessao log = this.loginRepository.findByToken(tokenRetiradoDoHeader)
				.orElseThrow(() -> new RuntimeException("erro ao coletar o token"));

		Usuario usuarioEncontradoPeloToken = this.loginRepository.findUsuarioByToken(tokenRetiradoDoHeader)
				.orElseThrow(() -> new IllegalArgumentException("Erro"));

		return ResponseEntity.ok(this.usuarioService.resetPassword(confirmaSenha, senha, usuarioEncontradoPeloToken));

	}

	@PutMapping("/stats/inactive")
	public boolean inativarConta(@RequestHeader("Authorization") String header, @RequestParam String senha) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioByToken = this.loginRepository.findUsuarioByToken(token)
				.orElseThrow(() -> new RuntimeException("Nao encontrado usuario"));
		return this.usuarioService.inativarConta(usuarioByToken, senha, token);

	}

	@PutMapping("/stats/active")
	public boolean ativarConta(@RequestParam String email, @RequestParam String cpf, @RequestParam String senha) {

		return this.usuarioService.ativarConta(email, cpf, senha);

	}

	@PutMapping
	public ResponseEntity<UsuarioResponseGeneric> atualizarCadastro(@RequestHeader("Authorization") String header,
			@RequestBody UsuarioRequest atualizacao) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuario = this.loginRepository.findUsuarioByToken(token)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Usuário não encontrado"));

		UsuarioResponseGeneric resposta = this.usuarioService.updateUsuario(usuario, atualizacao);

		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/me")
	public UsuarioResponseGeneric meuPerfil(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioBuscadoNoFront = this.loginRepository.findUsuarioByToken(token)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return new UsuarioResponseGeneric(usuarioBuscadoNoFront.getId(), usuarioBuscadoNoFront.getNome(),
				usuarioBuscadoNoFront.getEmail(), usuarioBuscadoNoFront.getPerfilDoUsuario().getDescProgramador());
	}

}
