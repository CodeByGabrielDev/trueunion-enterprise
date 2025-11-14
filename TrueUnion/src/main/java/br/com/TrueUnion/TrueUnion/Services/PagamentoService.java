package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.PagamentoRequest;
import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.Pagamento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryDespesa;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryFormaPagamento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryPagamento;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.PagamentoResponse;

@Service
public class PagamentoService {

	@Autowired
	RepositoryDespesa repoDespesa;
	@Autowired
	RepositoryFormaPagamento repoFormaPgto;
	@Autowired
	PagamentoParcelasService serviceParcelas;
	@Autowired
	RepositoryPagamento repoPagamento;

	public PagamentoResponse registrarPagamento(Usuario usuarioPegoNofront, PagamentoRequest pagamento, int idDespesa,
			int idFormaPgto) {
		// FormaPgtos formaPagamento, Despesas despesa

		Despesas despesa = this.repoDespesa.findById(idDespesa)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (despesa.getEvent().getDonoEvento().getId() != usuarioPegoNofront.getId()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		} else {
			if (pagamento.getValor() > despesa.getValor()) {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
			} else {
				Pagamento pagamentoEntity = new Pagamento(pagamento.getValor(), pagamento.getQuantidadeParcelas(),
						this.repoFormaPgto.findById(idFormaPgto)
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
				pagamentoEntity.setDataPagamento(LocalDate.now());
				pagamentoEntity.setDespesa(despesa);
				this.repoPagamento.save(pagamentoEntity);
				this.serviceParcelas.CriarParcelas(pagamentoEntity);

				return new PagamentoResponse(pagamentoEntity.getId(), pagamentoEntity.getValor(),
						pagamentoEntity.getQuantidadeParcelas(),
						this.repoFormaPgto.findById(idFormaPgto)
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
								.getTipoPagamento(),
						despesa.getCategoria().getNomeCategoria());

			}

		}

	}
}
