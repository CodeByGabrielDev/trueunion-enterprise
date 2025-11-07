package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.UsuarioRequest;
import br.com.TrueUnion.TrueUnion.Entities.PerfilUsuario;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryHasherInativador;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryPerfilUsuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	RepositoryUsuario usuarioRepository;

	@Autowired
	RepositoryLoginSessao loginRepository;
	@Autowired
	RepositoryPerfilUsuario perfil;
	@Autowired
	RepositoryHasherInativador hash;

	@Transactional
	public UsuarioResponseGeneric createUsuario(UsuarioRequest usu, int tipoDeUsuario) {
		if (usu == null) {
			throw new IllegalArgumentException("Erro: o objeto de usuário está nulo.");
		}
		PerfilUsuario perfil = this.perfil.findById(tipoDeUsuario)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// Validação de senha — deve conter letras e números
		boolean temLetra = false;
		boolean temNumero = false;

		for (char c : usu.getSenha().toCharArray()) {
			if (Character.isLetter(c)) {
				temLetra = true;
			} else if (Character.isDigit(c)) {
				temNumero = true;
			}
		}

		if (!temLetra || !temNumero) {
			throw new IllegalArgumentException("A senha deve conter letras e números.");
		}

		// Validação de CPF
		if (!Utils.cpfValido(usu.getCpf())) {
			throw new IllegalArgumentException("CPF inválido. Valide os campos e tente novamente.");
		}

		// Criação do usuário
		Usuario usuario = new Usuario();
		usuario.setNome(usu.getNome());
		usuario.setCpf(usu.getCpf());
		usuario.setEmail(usu.getEmail());
		usuario.setSenha(usu.getSenha());
		usuario.setTelefone(usu.getTelefone());
		usuario.setDataCadastramento(LocalDate.now());
		usuario.setInativo(false);
		usuario.setPerfilDoUsuario(perfil);
		this.usuarioRepository.save(usuario);

		return new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(), usuario.getEmail(),
				usuario.getPerfilDoUsuario().getDescProgramador());

	}

	@Transactional
	public UsuarioResponseGeneric updateUsuario(Usuario usuario, UsuarioRequest atualizacao) {

		if (atualizacao.getNome() != null && !atualizacao.getNome().isBlank()) {
			usuario.setNome(atualizacao.getNome());
		}

		if (atualizacao.getEmail() != null && !atualizacao.getEmail().isBlank()) {
			usuario.setEmail(atualizacao.getEmail());
		}

		if (atualizacao.getTelefone() != null && !atualizacao.getTelefone().isBlank()) {
			usuario.setTelefone(atualizacao.getTelefone());
		}

		usuarioRepository.save(usuario);

		return new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(), usuario.getEmail(),
				usuario.getPerfilDoUsuario().getDescProgramador());
	}

	public UsuarioResponseGeneric buscarUsuarioPorId(int id) {
		Usuario usuario = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("OBJETO USUARIO INEXISTENTE COM ESSE ID"));
		UsuarioResponseGeneric usuarioResponse = new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(),
				usuario.getEmail(), usuario.getPerfilDoUsuario().getDescProgramador());
		return usuarioResponse;

	}

	@Transactional
	public boolean inativarConta(Usuario usuarioPeloFront, String senha, String tokenUsuario) {
		if (usuarioPeloFront.getSenha().equals(senha)) {
			usuarioPeloFront.setInativo(true);
			this.loginRepository.deleteByToken(tokenUsuario);
			this.usuarioRepository.save(usuarioPeloFront);
			return true;
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro, senha incorreta!");
		}

	}

	@Transactional
	public boolean ativarConta(String email, String cpf, String senha) {
		Usuario usuarioColetadoViaEmail = this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
		Usuario validarUsuarioPeloCpf = this.usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
		String senhaDoUsuarioInativo = this.hash.pegarSenhaDoUsuarioViaId(validarUsuarioPeloCpf);
		if (usuarioColetadoViaEmail.getId() == validarUsuarioPeloCpf.getId()) {
			if (senha.equals(senhaDoUsuarioInativo)) {
				usuarioColetadoViaEmail.setInativo(false);
				return true;
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "SENHA INCORRETA PARA ATIVACAO DE CONTA");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"Ocorreu um erro, ao validar, o id  do usuario buscado pelo cpf nao esta de acordo com  id do usuario buscado pelo email");
		}

	}

	@Transactional
	public UsuarioResponseGeneric resetPassword(String senha, String confirmarSenha, Usuario usuario) {
		Usuario testeSenha = this.usuarioRepository.findById(usuario.getId()).orElseThrow();
		if (testeSenha.getSenha().equals(usuario.getSenha())) {
			boolean temLetra = false;
			boolean temNumero = false;

			for (char c : senha.toCharArray()) {
				if (Character.isLetter(c)) {
					temLetra = true;
				} else if (Character.isDigit(c)) {
					temNumero = true;
				}
			}

			if (!temLetra || !temNumero) {
				throw new IllegalArgumentException("A senha deve conter letras e números");
			} else {
				Usuario usuarioNoBanco = this.usuarioRepository.findById(usuario.getId())
						.orElseThrow(() -> new IllegalArgumentException("Objeto inexistente"));

				usuarioNoBanco.setSenha(senha);
				UsuarioResponseGeneric respostaNoPut = new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(),
						usuario.getEmail(), usuario.getPerfilDoUsuario().getDescProgramador());
				this.usuarioRepository.save(usuarioNoBanco);
				return respostaNoPut;
			}

		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta!");
		}

	}

	public List<UsuarioResponseGeneric> listarUsuariosDisponiveis() {
		Iterable<Usuario> usuariosEncontrados = this.usuarioRepository.findAllUsuarioAtivo();
		List<UsuarioResponseGeneric> usuariosResponseLista = new ArrayList<>();
		for (Usuario usu : usuariosEncontrados) {
			UsuarioResponseGeneric usuarioGeneric = new UsuarioResponseGeneric(usu.getId(), usu.getNome(),
					usu.getEmail(), usu.getPerfilDoUsuario().getDescProgramador());
			usuariosResponseLista.add(usuarioGeneric);
		}
		return usuariosResponseLista;
	}

}
