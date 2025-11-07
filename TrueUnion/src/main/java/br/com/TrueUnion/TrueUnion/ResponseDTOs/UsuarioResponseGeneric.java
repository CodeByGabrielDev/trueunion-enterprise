package br.com.TrueUnion.TrueUnion.ResponseDTOs;

import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public class UsuarioResponseGeneric {
	private int id;
	private String name;
	private String email;
	private String perfil;

	public UsuarioResponseGeneric(int id, String name, String email, String perfil) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.perfil = perfil;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
