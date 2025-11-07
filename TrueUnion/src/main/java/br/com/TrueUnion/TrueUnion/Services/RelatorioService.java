package br.com.TrueUnion.TrueUnion.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.RelatorioDespesasEmEventos;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryRelatorioEventos;
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
		if (eventoVinculadoADespesaF.getTotalDeDespesasAtuais() == null) {
			despesas = despesa.getValor();
			eventoVinculadoADespesaF.setTotalDeDespesasAtuais(despesas);
		}
		if (eventoVinculadoADespesaF.getTotalDeDespesasAtuais() > eventoVinculadoADespesaF.getOrcamentoEvento()
				&& !eventoVinculadoADespesaF.isPassouOrcamento()) {
			eventoVinculadoADespesaF.setPassouOrcamento(true);
		} else {
			despesas = despesa.getValor() + eventoVinculadoADespesaF.getTotalDeDespesasAtuais();
			eventoVinculadoADespesaF.setTotalDeDespesasAtuais(despesas);
			this.relatorio.save(relatorio);
			this.evento.save(eventoVinculadoADespesaF);
		}
	}

}
