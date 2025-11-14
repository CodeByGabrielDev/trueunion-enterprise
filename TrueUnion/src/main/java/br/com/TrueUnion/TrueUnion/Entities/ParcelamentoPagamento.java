package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parcelamento_pagamento")
public class ParcelamentoPagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "numero_parcela")
	private int numeroParcela;
	@Column(name = "valor_parcela")
	private Double valorDaParcela;
	@ManyToOne
	@JoinColumn(name = "id_pagamento")
	private Pagamento pagamento;
	@Column(name = "data_pagamento")
	private LocalDateTime dataPgamento;

	private boolean atrasada;

	private boolean paga;

	public ParcelamentoPagamento() {

	}

	public ParcelamentoPagamento(int numeroParcela, Double valorDaParcela, Pagamento pagamento) {
		super();
		this.numeroParcela = numeroParcela;
		this.valorDaParcela = valorDaParcela;
		this.pagamento = pagamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Double getValorDaParcela() {
		return valorDaParcela;
	}

	public void setValorDaParcela(Double valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public LocalDateTime getDataPgamento() {
		return dataPgamento;
	}

	public void setDataPgamento(LocalDateTime dataPgamento) {
		this.dataPgamento = dataPgamento;
	}

	public boolean isAtrasada() {
		return atrasada;
	}

	public void setAtrasada(boolean atrasada) {
		this.atrasada = atrasada;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

}
