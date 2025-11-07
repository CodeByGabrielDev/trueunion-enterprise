package br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs;

import java.time.LocalDate;

import br.com.TrueUnion.TrueUnion.Entities.Evento;

public class TarefaRequest {

	private int id;
	private String desc;
	private LocalDate prazo;

	public TarefaRequest(String desc, LocalDate prazo) {
		super();
		this.desc = desc;
		this.prazo = prazo;
		
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

	
	
	
}
