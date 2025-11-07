package br.com.TrueUnion.TrueUnion.Controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.DespesasRequest;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.DespesaResponse;
import br.com.TrueUnion.TrueUnion.Services.DespesasService;

@RestController
@RequestMapping("/api-trueunion/expense")
public class DespesaController {
	@Autowired
	RepositoryLoginSessao login;

	@Autowired
	DespesasService despesaService;

	@PostMapping("/event/{idEvento}/expense")
	public DespesaResponse criarUmaDespesa(@RequestHeader("Authorization") String header,
			@RequestBody DespesasRequest despesa, @RequestParam int idCategoria, @PathVariable int idEvento) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuario = this.login.findUsuarioByToken(token)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return this.despesaService.criarDespesa(usuario, despesa, idCategoria, idEvento);
	}

	@GetMapping
	public List<DespesaResponse> mostrarDespesas(@RequestHeader("Authorization") String header) {
		String token = header.replace("Bearer ", "").trim();
		Usuario usuario = this.login.findUsuarioByToken(token).orElseThrow(() -> new RuntimeErrorException(null));
		return this.despesaService.buscarDespesas(usuario);
	}
	
	
}
