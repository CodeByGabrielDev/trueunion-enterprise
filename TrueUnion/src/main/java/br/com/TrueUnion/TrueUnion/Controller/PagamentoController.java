package br.com.TrueUnion.TrueUnion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.PagamentoRequest;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.PagamentoResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.ParcelamentoPagamentoResponse;
import br.com.TrueUnion.TrueUnion.Services.LoginService;
import br.com.TrueUnion.TrueUnion.Services.PagamentoParcelasService;
import br.com.TrueUnion.TrueUnion.Services.PagamentoService;

@RestController
@RequestMapping("/api-trueunion/payment")
public class PagamentoController {
	@Autowired
	LoginService loginService;
	@Autowired
	PagamentoService payment;
	@Autowired
	PagamentoParcelasService parcela;

	@PostMapping("/expense/{idDespesa}/payment")
	public PagamentoResponse criarPagamento(@RequestHeader("Authorization") String header,
			@RequestBody PagamentoRequest pagamento, @PathVariable int idDespesa, @RequestParam int idFormaPgto) {

		String token = header.replace("Bearer ", "").trim();
		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);

		return this.payment.registrarPagamento(usuarioPegoPeloToken, pagamento, idDespesa, idFormaPgto);

	}

	@PutMapping("/expense/{idDespesa}/payment/{idPagamento}/portion")
	public ParcelamentoPagamentoResponse realizarPagamentoDeParcela(@RequestHeader("Authorization") String header,
			@PathVariable int idDespesa, @PathVariable int idPagamento, @RequestParam int idParcela) {

		String token = header.replace("Bearer ", "").trim();
		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);

		return this.parcela.realizarPagamentoDeParcela(usuarioPegoPeloToken, idDespesa, idPagamento, idParcela);
	}

}
