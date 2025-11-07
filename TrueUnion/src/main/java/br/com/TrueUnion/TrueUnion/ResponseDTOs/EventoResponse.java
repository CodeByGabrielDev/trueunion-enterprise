package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class EventoResponse {

	private int id;
	private String nome;
	private Double orcamento;
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String descricao;

	public EventoResponse(int id, String nome, Double orcamento, String local, LocalDate dataInicio, LocalDate dataFim,
			String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.orcamento = orcamento;
		this.local = local;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Double orcamento) {
		this.orcamento = orcamento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
