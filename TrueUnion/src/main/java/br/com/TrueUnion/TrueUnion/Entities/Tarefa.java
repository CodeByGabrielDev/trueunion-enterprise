package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarefas")
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "desc_tarefa")
	private String desc;
	@Column(name = "prazo_tarefa")
	private LocalDate prazo;
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;
	@Column
	private boolean atrasada;

	public Tarefa() {
		super();
	}

	public Tarefa(String desc, LocalDate prazo, Evento evento) {
		super();
		this.desc = desc;
		this.prazo = prazo;
		this.evento = evento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean isAtrasada() {
		return atrasada;
	}

	public void setAtrasada(boolean atrasada) {
		this.atrasada = atrasada;
	}

}
