package br.com.TrueUnion.TrueUnion.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.RelatorioDespesasEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryRelatorioEventos;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.RelatorioResponse;
import jakarta.transaction.Transactional;

@Service
public class RelatorioService {
	@Autowired
	RepositoryRelatorioEventos relatorio;
	@Autowired
	RepositoryEvento evento;

	@Transactional
	public void alimentaRelatorio(Despesas despesa, Evento eventoVinculadoADespesaF) {
		RelatorioDespesasEmEventos relatorio = new RelatorioDespesasEmEventos(eventoVinculadoADespesaF,
				despesa.getValor());
		relatorio.setOrcamentoTotal(eventoVinculadoADespesaF.getOrcamentoEvento());
		relatorio.setNomeEvento(eventoVinculadoADespesaF.getNome());
		double despesas = 0;

		if (despesa.getValor() > eventoVinculadoADespesaF.getOrcamentoEvento() || eventoVinculadoADespesaF
				.getTotalDeDespesasAtuais() > eventoVinculadoADespesaF.getOrcamentoEvento()) {
			eventoVinculadoADespesaF.setPassouOrcamento(true);
			relatorio.setEstourouOrcamento(true);

			despesas = despesa.getValor() + eventoVinculadoADespesaF.getTotalDeDespesasAtuais();
			eventoVinculadoADespesaF.setTotalDeDespesasAtuais(despesas);
			this.evento.save(eventoVinculadoADespesaF);
			this.relatorio.save(relatorio);

		} else {

			despesas = despesa.getValor() + eventoVinculadoADespesaF.getTotalDeDespesasAtuais();
			eventoVinculadoADespesaF.setTotalDeDespesasAtuais(despesas);
			this.evento.save(eventoVinculadoADespesaF);
			this.relatorio.save(relatorio);
		}

	}

	public List<RelatorioResponse> tirarRelatorio(Usuario usuarioAtual, int idEventoF) {
		Evento eventoBuscadoNoFront = this.evento.findById(idEventoF)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		List<RelatorioDespesasEmEventos> totalRelatoriosPorEvento = this.relatorio
				.searchRelatorioByIdEvento(eventoBuscadoNoFront, usuarioAtual);
		List<RelatorioResponse> respostaEmTela = new ArrayList<>();
		for (RelatorioDespesasEmEventos r : totalRelatoriosPorEvento) {
			RelatorioResponse relatorioEmTela = new RelatorioResponse(r.getId(), r.getTotalDespesas(),
					r.getOrcamentoTotal(), r.getNomeEvento());
			respostaEmTela.add(relatorioEmTela);
		}
		return respostaEmTela;

	}
}
