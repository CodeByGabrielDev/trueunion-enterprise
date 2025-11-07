package br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs;

import java.time.LocalDate;
import java.util.List;

import br.com.TrueUnion.TrueUnion.Entities.CategoriaDespesa;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Pagamento;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

public class DespesasRequest {

	@NotBlank
	private Double valorDaDespesa;
	@NotBlank
	private LocalDate dataDeRegistro;

	private String desc;

	public DespesasRequest(Double valorDaDespesa, LocalDate dataDeRegistro, String desc) {
		super();
		this.valorDaDespesa = valorDaDespesa;
		this.dataDeRegistro = dataDeRegistro;
		this.desc = desc;
	}

	public Double getValorDaDespesa() {
		return valorDaDespesa;
	}

	public void setValorDaDespesa(Double valorDaDespesa) {
		this.valorDaDespesa = valorDaDespesa;
	}

	public LocalDate getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(LocalDate dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
