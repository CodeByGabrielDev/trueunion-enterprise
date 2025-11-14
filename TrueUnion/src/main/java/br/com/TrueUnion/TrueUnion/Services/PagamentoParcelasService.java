package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Pagamento;
import br.com.TrueUnion.TrueUnion.Entities.ParcelamentoPagamento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryParcelasPagamento;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.ParcelamentoPagamentoResponse;

@Service
public class PagamentoParcelasService {

	@Autowired
	RepositoryParcelasPagamento repositoryParcelas;
	@Autowired
	RepositoryEvento repositoryEvento;

	public void CriarParcelas(Pagamento pagamento) {

		for (int i = 1; i <= pagamento.getQuantidadeParcelas(); i++) {
			ParcelamentoPagamento parcelas = new ParcelamentoPagamento(pagamento.getQuantidadeParcelas(),
					(pagamento.getValor() / pagamento.getQuantidadeParcelas()), pagamento);
			parcelas.setPaga(false);
			parcelas.setAtrasada(false);
			parcelas.setNumeroParcela(i);
			this.repositoryParcelas.save(parcelas);
		}
	}
	public ParcelamentoPagamentoResponse realizarPagamentoDeParcela(Usuario usuarioNoFront, int idDespesa,
			int idPagametno, int idParcelaRespectiva) {
		ParcelamentoPagamento parcela = this.repositoryParcelas.findById(idParcelaRespectiva)
				.orElseThrow(() -> new RuntimeException());
		Evento eventoVinculadoAparcela = this.repositoryEvento
				.findById(parcela.getPagamento().getDespesa().getEvent().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.ALREADY_REPORTED));

		if (parcela.getPagamento().getId() == idPagametno && parcela.getPagamento().getDespesa().getId() == idDespesa) {
			if (usuarioNoFront.getId() == parcela.getPagamento().getDespesa().getEvent().getDonoEvento().getId()) {
				parcela.setPaga(true);
				parcela.setDataPgamento(LocalDateTime.now());
				this.repositoryParcelas.save(parcela);
				List<ParcelamentoPagamento> parcelasPagas = this.repositoryParcelas
						.buscarParcelasPagas(parcela.getPagamento());

				if (parcelasPagas.size() < parcela.getPagamento().getQuantidadeParcelas()) {
					double interadorDeParcelas = 0;
					for (ParcelamentoPagamento p : parcelasPagas) {
						interadorDeParcelas = p.getValorDaParcela() + interadorDeParcelas;
					}
					eventoVinculadoAparcela.setTotalDeDespesasAtuais(
							eventoVinculadoAparcela.getTotalDeDespesasAtuais() - interadorDeParcelas);
					this.repositoryEvento.save(eventoVinculadoAparcela);
					parcela.getPagamento().getDespesa().setPago(true);

				}
				return new ParcelamentoPagamentoResponse(parcela.getId(), parcela.getNumeroParcela(),
						parcela.getValorDaParcela(),
						parcela.getPagamento().getDespesa().getCategoria().getNomeCategoria(),
						parcela.getDataPgamento());
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}

		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
	}

}
