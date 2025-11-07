package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import java.time.LocalDate;
import java.util.List;

import br.com.TrueUnion.TrueUnion.Entities.CategoriaDespesa;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Pagamento;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class DespesaResponse {

	private int id;
	private String categoria;
	private double valor;
	private LocalDate data;
	private String nomeEvento;
	private String desc;

	public DespesaResponse(int id, String categoria, double valor, LocalDate data, String nomeEvento, String desc) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.valor = valor;
		this.data = data;
		this.nomeEvento = nomeEvento;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
