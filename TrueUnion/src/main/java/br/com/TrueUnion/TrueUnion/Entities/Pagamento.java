package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "valor_pago")
	private double valor;
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	@Column(name = "qtd_parcelas")
	private int quantidadeParcelas;
	@ManyToOne
	@JoinColumn(name = "id_forma_pgto")
	private FormaPgtos formaPagamento;
	@ManyToOne
	@JoinColumn(name = "id_despesa")
	private Despesas despesa;
	@OneToMany(mappedBy = "pagamento")
	private List<ParcelamentoPagamento>parcelas;
	public Pagamento() {

	}

	public Pagamento(double valor, int quantidadeParcelas, FormaPgtos formaPagamento) {
		this.valor = valor;
		dataPagamento = null;
		this.quantidadeParcelas = quantidadeParcelas;
		this.formaPagamento = formaPagamento;
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

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public FormaPgtos getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPgtos formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Despesas getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesas despesa) {
		this.despesa = despesa;
	}

}
