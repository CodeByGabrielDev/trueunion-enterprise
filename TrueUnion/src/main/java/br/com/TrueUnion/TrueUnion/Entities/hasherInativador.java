package br.com.TrueUnion.TrueUnion.Entities;

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
@Table(name = "SENHA_SEQUESTRADA_DE_CONTAS_INATIVAS")
public class hasherInativador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "SENHA_USUARIO_INATIVO")
	private String senha;
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuarios;

	public hasherInativador(int id, String senha, Usuario usuarios) {
		super();
		this.id = id;
		this.senha = senha;
		this.usuarios = usuarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

}
