package br.com.TrueUnion.TrueUnion.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.UsuarioRequest;
import br.com.TrueUnion.TrueUnion.Entities.PerfilUsuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import jakarta.transaction.Transactional;

@Service
public class CadastrarUsuarioService {
	@Autowired
	RepositoryUsuario repositoryUsuario;
	@Autowired
	UsuarioService usuarioService;

	@Transactional
	public void cadastrarUsuario(UsuarioRequest usuario, int tipoDeUsuario) {

		repositoryUsuario.findByEmail(usuario.getEmail()).ifPresent(u -> {
			throw new IllegalArgumentException("Usuário já cadastrado com esse e-mail");
		});

		repositoryUsuario.findByCpf(usuario.getCpf()).ifPresent(u -> {
			throw new IllegalArgumentException("Usuário já cadastrado com esse CPF");
		});

		this.usuarioService.createUsuario(usuario, tipoDeUsuario);
	}

}
