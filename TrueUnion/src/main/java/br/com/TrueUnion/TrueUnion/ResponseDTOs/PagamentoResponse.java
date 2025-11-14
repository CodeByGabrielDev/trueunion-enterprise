package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.FormaPgtos;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PagamentoResponse {

	private int id;
	private double valor;
	private int quantidadeParcelas;
	private String formaPagamento;
	private String categoriaDespesa;

	public PagamentoResponse(int id, double valor, int quantidadeParcelas, String formaPagamento,
			String categoriaDespesa) {
		super();
		this.id = id;
		this.valor = valor;
		this.quantidadeParcelas = quantidadeParcelas;
		this.formaPagamento = formaPagamento;
		this.categoriaDespesa = categoriaDespesa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getCategoriaDespesa() {
		return categoriaDespesa;
	}

	public void setCategoriaDespesa(String categoriaDespesa) {
		this.categoriaDespesa = categoriaDespesa;
	}

}
