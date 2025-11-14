package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.DespesasRequest;
import br.com.TrueUnion.TrueUnion.Entities.CategoriaDespesa;
import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryCategoriaDespesa;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryDespesa;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.DespesaResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.EventoResponse;
import jakarta.transaction.Transactional;

@Service
public class DespesasService {
	@Autowired
	RepositoryEvento event;
	@Autowired
	EventoService evento;
	@Autowired
	RepositoryCategoriaDespesa categoria;
	@Autowired
	RepositoryDespesa despesa;
	@Autowired
	RelatorioService relatorio;

	@Transactional
	public DespesaResponse criarDespesa(Usuario usuarioLogado, DespesasRequest despesa, int idCategoria, int idEvento) {
		CategoriaDespesa categoria = this.categoria.findById(idCategoria)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		Evento evento = this.event.findById(idEvento)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (evento.getDonoEvento().getId() == usuarioLogado.getId() && !evento.isConcluido() && !evento.isCancelada()) {
			Despesas despesaEntity = new Despesas(categoria, despesa.getValorDaDespesa(), evento, despesa.getDesc());
			this.despesa.save(despesaEntity);
			this.relatorio.alimentaRelatorio(despesaEntity, evento);
			return new DespesaResponse(despesaEntity.getId(), despesaEntity.getCategoria().getNomeCategoria(),
					despesaEntity.getValor(), despesaEntity.getData(), despesaEntity.getEvent().getNome(),
					despesaEntity.getDesc());
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	}

	public List<DespesaResponse> buscarDespesas(Usuario usuario) {
		List<Despesas> despesas = this.despesa.findDespesasVinculadosAoUsuario(usuario);
		List<DespesaResponse> respostaEmTela = new ArrayList<>();
		for (Despesas d : despesas) {
			DespesaResponse despesa = new DespesaResponse(d.getId(), d.getCategoria().getNomeCategoria(), d.getValor(),
					d.getData(), d.getEvent().getNome(), d.getDesc());
			respostaEmTela.add(despesa);
		}

		return respostaEmTela;

	}

}
