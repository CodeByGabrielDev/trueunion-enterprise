package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Entities.Evento;

public class TarefaResponse {

	private String desc;
	private LocalDate prazo;
	private String evento;
	private boolean atrasada;

	public TarefaResponse(String desc, LocalDate prazo, String evento, boolean atrasada) {
		super();
		this.desc = desc;
		this.prazo = prazo;
		this.evento = evento;
		this.atrasada = atrasada;
	}

	public boolean isAtrasada() {
		return atrasada;
	}

	public void setAtrasada(boolean atrasada) {
		this.atrasada = atrasada;
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

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

}
