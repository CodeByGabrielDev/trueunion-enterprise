package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RelatorioResponse {

	private int id;
	private double total_Despesas;
	private double orcamento_Total;
	private String nome_evento;

	public RelatorioResponse(int id, double total_Despesas, double orcamento_Total, String nome_evento) {
		super();
		this.id = id;
		this.total_Despesas = total_Despesas;
		this.orcamento_Total = orcamento_Total;
		this.nome_evento = nome_evento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal_Despesas() {
		return total_Despesas;
	}

	public void setTotal_Despesas(double total_Despesas) {
		this.total_Despesas = total_Despesas;
	}

	public double getOrcamento_Total() {
		return orcamento_Total;
	}

	public void setOrcamento_Total(double orcamento_Total) {
		this.orcamento_Total = orcamento_Total;
	}

	public String getNome_evento() {
		return nome_evento;
	}

	public void setNome_evento(String nome_evento) {
		this.nome_evento = nome_evento;
	}
	
	
}
