package br.com.TrueUnion.TrueUnion.ResponseDTOs;

public class DashboardFinanceiroDeUmEvento {

	private int id;
	private String nome_evento;
	private String nome_dono_evento;
	private double quantidade_pendente_pagar;
	private double orcamento_estipulado_evento;
	private int quantidade_convidados_que_aceitaram;
	private int quantidade_convidados_que_recusaram;
	private int quantidade_convidados_pendentes;

	public DashboardFinanceiroDeUmEvento(int id, String nome_evento, String nome_dono_evento,
			double quantidade_pendente_pagar, double orcamento_estipulado_evento,
			int quantidade_convidados_que_aceitaram, int quantidade_convidados_que_recusaram,
			int quantidade_convidados_pendentes) {
		super();
		this.id = id;
		this.nome_evento = nome_evento;
		this.nome_dono_evento = nome_dono_evento;
		this.quantidade_pendente_pagar = quantidade_pendente_pagar;
		this.orcamento_estipulado_evento = orcamento_estipulado_evento;
		this.quantidade_convidados_que_aceitaram = quantidade_convidados_que_aceitaram;
		this.quantidade_convidados_que_recusaram = quantidade_convidados_que_recusaram;
		this.quantidade_convidados_pendentes = quantidade_convidados_pendentes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_evento() {
		return nome_evento;
	}

	public void setNome_evento(String nome_evento) {
		this.nome_evento = nome_evento;
	}

	public String getNome_dono_evento() {
		return nome_dono_evento;
	}

	public void setNome_dono_evento(String nome_dono_evento) {
		this.nome_dono_evento = nome_dono_evento;
	}

	public double getQuantidade_pendente_pagar() {
		return quantidade_pendente_pagar;
	}

	public void setQuantidade_pendente_pagar(double quantidade_pendente_pagar) {
		this.quantidade_pendente_pagar = quantidade_pendente_pagar;
	}

	public double getOrcamento_estipulado_evento() {
		return orcamento_estipulado_evento;
	}

	public void setOrcamento_estipulado_evento(double orcamento_estipulado_evento) {
		this.orcamento_estipulado_evento = orcamento_estipulado_evento;
	}

	public int getQuantidade_convidados_que_aceitaram() {
		return quantidade_convidados_que_aceitaram;
	}

	public void setQuantidade_convidados_que_aceitaram(int quantidade_convidados_que_aceitaram) {
		this.quantidade_convidados_que_aceitaram = quantidade_convidados_que_aceitaram;
	}

	public int getQuantidade_convidados_que_recusaram() {
		return quantidade_convidados_que_recusaram;
	}

	public void setQuantidade_convidados_que_recusaram(int quantidade_convidados_que_recusaram) {
		this.quantidade_convidados_que_recusaram = quantidade_convidados_que_recusaram;
	}

	public int getQuantidade_convidados_pendentes() {
		return quantidade_convidados_pendentes;
	}

	public void setQuantidade_convidados_pendentes(int quantidade_convidados_pendentes) {
		this.quantidade_convidados_pendentes = quantidade_convidados_pendentes;
	}

}
