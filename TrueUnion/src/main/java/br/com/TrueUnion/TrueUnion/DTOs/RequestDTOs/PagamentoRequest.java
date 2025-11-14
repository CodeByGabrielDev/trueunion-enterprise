package br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs;

import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.FormaPgtos;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PagamentoRequest {
	private double valor;
	private int quantidadeParcelas;

	public PagamentoRequest(double valor, int quantidadeParcelas) {
		super();
		this.valor = valor;
		this.quantidadeParcelas = quantidadeParcelas;

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


}
