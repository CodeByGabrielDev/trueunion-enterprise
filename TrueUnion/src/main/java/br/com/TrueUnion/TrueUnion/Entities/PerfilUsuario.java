package br.com.TrueUnion.TrueUnion.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil_usuario")
public class PerfilUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "desc_programador")
	private String descProgramador;
	@OneToMany(mappedBy = "perfilDoUsuario")
	private List<Usuario>usuarios = new ArrayList<>();
	
	
	public PerfilUsuario() {
		
	}
	
	public PerfilUsuario(String descProgramador) {
		super();
		this.descProgramador = descProgramador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescProgramador() {
		return descProgramador;
	}

	public void setDescProgramador(String descProgramador) {
		this.descProgramador = descProgramador;
	}

}
