package br.com.TrueUnion.TrueUnion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.UsuarioRequest;
import br.com.TrueUnion.TrueUnion.Entities.PerfilUsuario;
import br.com.TrueUnion.TrueUnion.Services.CadastrarUsuarioService;
import br.com.TrueUnion.TrueUnion.Services.LoginService;

@RestController
@RequestMapping("/api-trueunion/auth")
public class PaginaPrincipal {
	@Autowired
	LoginService loginService;
	@Autowired
	CadastrarUsuarioService cadastrarService;

	// 123123
	@GetMapping("/login")
	public void logar(@RequestParam String email, @RequestParam String senha) {
		this.loginService.login(email, senha);
	}

	@PostMapping("/register")
	public void cadastrarUsuario(@RequestBody UsuarioRequest usuario, @RequestParam int tipoDeUsuario) {
		this.cadastrarService.cadastrarUsuario(usuario,tipoDeUsuario);

	}
}
