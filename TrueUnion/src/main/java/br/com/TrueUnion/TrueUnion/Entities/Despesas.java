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
@Table(name = "despesas")
public class Despesas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_categoria_despesa")
	private CategoriaDespesa categoria;
	@Column(name = "valor", nullable = false)
	private double valor;
	@Column(name = "data_despesa")
	private LocalDate data;
	@ManyToOne
	@JoinColumn(name = "id_evento")
	private Evento event;
	@Column(name = "desc_despesa", nullable = true)
	private String desc;
	@OneToMany(mappedBy = "despesa")
	private List<Pagamento> pagamentos;
	@Column(name = "despesa_paga")
	private boolean pago;
	

	public Despesas() {

	}

	public Despesas(CategoriaDespesa categoria, double valor, Evento event, String desc) {
		super();
		this.categoria = categoria;
		this.valor = valor;
		this.event = event;
		this.desc = desc;
		this.data = LocalDate.now(); // ao realizar a instancia ja salva a data feita
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	
	

}
