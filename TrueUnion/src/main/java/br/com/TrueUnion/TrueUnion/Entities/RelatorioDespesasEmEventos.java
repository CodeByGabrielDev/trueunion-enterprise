package br.com.TrueUnion.TrueUnion.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "relatorio_despesas")
public class RelatorioDespesasEmEventos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_evento_vinculado")
	private Evento eventoVinculado;

	private Double totalDespesas;

	private Double orcamentoTotal;

	private String nomeEvento;

	public RelatorioDespesasEmEventos(Evento eventoVinculado, Double totalDespesas) {
		super();
		this.eventoVinculado = eventoVinculado;
		this.totalDespesas = totalDespesas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evento getEventoVinculado() {
		return eventoVinculado;
	}

	public void setEventoVinculado(Evento eventoVinculado) {
		this.eventoVinculado = eventoVinculado;
	}

	public Double getTotalDespesas() {
		return totalDespesas;
	}

	public void setTotalDespesas(Double totalDespesas) {
		this.totalDespesas = totalDespesas;
	}

	public Double getOrcamentoTotal() {
		return orcamentoTotal;
	}

	public void setOrcamentoTotal(Double orcamentoTotal) {
		this.orcamentoTotal = orcamentoTotal;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

}
