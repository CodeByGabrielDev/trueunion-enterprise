package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import java.time.LocalDateTime;

import br.com.TrueUnion.TrueUnion.Entities.Pagamento;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ParcelamentoPagamentoResponse {
	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	 * 
	 * @Column(name = "numero_parcela") private int numeroParcela;
	 * 
	 * @Column(name = "valor_parcela") private Double valorDaParcela;
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_pagamento") private Pagamento pagamento;
	 * 
	 * @Column(name = "data_pagamento") private LocalDateTime dataPgamento;
	 */
	private int id;
	private int numero_da_parcela;
	private double valor_parcela;
	private String nome_da_despesa_paga;
	private LocalDateTime data_pagamento;

	public ParcelamentoPagamentoResponse(int id, int numero_da_parcela, double valor_parcela,
			String nome_da_despesa_paga, LocalDateTime data_pagamento) {
		super();
		this.id = id;
		this.numero_da_parcela = numero_da_parcela;
		this.valor_parcela = valor_parcela;
		this.nome_da_despesa_paga = nome_da_despesa_paga;
		this.data_pagamento = data_pagamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero_da_parcela() {
		return numero_da_parcela;
	}

	public void setNumero_da_parcela(int numero_da_parcela) {
		this.numero_da_parcela = numero_da_parcela;
	}

	public double getValor_parcela() {
		return valor_parcela;
	}

	public void setValor_parcela(double valor_parcela) {
		this.valor_parcela = valor_parcela;
	}

	public String getNome_da_despesa_paga() {
		return nome_da_despesa_paga;
	}

	public void setNome_da_despesa_paga(String nome_da_despesa_paga) {
		this.nome_da_despesa_paga = nome_da_despesa_paga;
	}

	public LocalDateTime getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(LocalDateTime data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

}
