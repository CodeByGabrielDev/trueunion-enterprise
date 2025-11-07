package br.com.TrueUnion.TrueUnion.ResponseDTOs;

public class ConvidadosEmEventosResponse {

	private String nomeEvento;
	private String nomeUsuario;
	private String statusConviteRSVP;

	public ConvidadosEmEventosResponse(String nomeEvento, String nomeUsuario, String statusConviteRSVP) {
		super();
		this.nomeEvento = nomeEvento;
		this.nomeUsuario = nomeUsuario;
		this.statusConviteRSVP = statusConviteRSVP;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getStatusConviteRSVP() {
		return statusConviteRSVP;
	}

	public void setStatusConviteRSVP(String statusConviteRSVP) {
		this.statusConviteRSVP = statusConviteRSVP;
	}

}
