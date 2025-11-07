package br.com.TrueUnion.TrueUnion.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "convidados_em_eventos")
public class ConvidadosEmEventos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario convidado;
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento evento;
	@ManyToOne
	@JoinColumn(name = "id_status_RSVP")
	private StatusRSVP status;
	
	// ESSA É UMA TABELA INTERMEDIARIA, OS ATRIBUTOS NA NOSSA CLASSE POSSIVELMENTE
	// SERÃO CUSTOMIZADOS
	// NEM TODA A CLASSE PRECISAMOS NECESSARIAMENTE SEGUIR O PADRÃO DA DOCUMENTAÇÃO

	public ConvidadosEmEventos() {

	}

	public ConvidadosEmEventos(Usuario convidado, Evento evento) {
		super();
		this.convidado = convidado;
		this.evento = evento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getConvidado() {
		return convidado;
	}

	public void setConvidado(Usuario convidado) {
		this.convidado = convidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public StatusRSVP getStatus() {
		return status;
	}

	public void setStatus(StatusRSVP status) {
		this.status = status;
	}	
	

}
