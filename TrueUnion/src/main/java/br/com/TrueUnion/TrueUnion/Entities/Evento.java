package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
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
@Table(name = "eventos")
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome_evento", nullable = false)
	private String nome;
	@Column(name = "orcamento_evento", nullable = false)
	private Double orcamentoEvento;
	@OneToMany(mappedBy = "evento")
	private List<ConvidadosEmEventos> listaDeConvidados = new ArrayList<>(); // RELACIONAMENTO COM TABELA INTERMEDIARIA
	@Column(name = "local_evento", nullable = false)
	private String local;//
	@Column(name = "data_inicio", nullable = false)
	private LocalDate dataInicio;
	@Column(name = "data_fim", nullable = false)
	private LocalDate dataFim;
	@Column(nullable = true)
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "id_dono_evento")
	private Usuario donoEvento;

	@Column
	private boolean cancelada;
	@Column(name = "motivo_cancelamento")
	private String motivoCancelamento;
	@OneToMany(mappedBy = "event")
	private List<Despesas> despesas;

	private boolean concluido;

	private boolean passouOrcamento;

	@Column(name = "total_despesas_atuais")
	private Double totalDeDespesasAtuais;

	public Evento() {

	}

	public Evento(String nome, double orcamentoEvento, String local, LocalDate dataInicio, LocalDate dataFim,
			String descricao) {
		super();
		this.nome = nome;
		this.orcamentoEvento = orcamentoEvento;
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

	public Double getOrcamentoEvento() {
		return orcamentoEvento;
	}

	public void setOrcamentoEvento(Double orcamentoEvento) {
		this.orcamentoEvento = orcamentoEvento;
	}

	public List<ConvidadosEmEventos> getListaDeConvidados() {
		return listaDeConvidados;
	}

	public void setListaDeConvidados(List<ConvidadosEmEventos> listaDeConvidados) {
		this.listaDeConvidados = listaDeConvidados;
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

	public Usuario getDonoEvento() {
		return donoEvento;
	}

	public void setDonoEvento(Usuario donoEvento) {
		this.donoEvento = donoEvento;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public List<Despesas> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesas> despesas) {
		this.despesas = despesas;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public boolean isPassouOrcamento() {
		return passouOrcamento;
	}

	public void setPassouOrcamento(boolean passouOrcamento) {
		this.passouOrcamento = passouOrcamento;
	}

	public Double getTotalDeDespesasAtuais() {
		return totalDeDespesasAtuais;
	}

	public void setTotalDeDespesasAtuais(Double totalDeDespesasAtuais) {
		this.totalDeDespesasAtuais = totalDeDespesasAtuais;
	}
	
	
}
