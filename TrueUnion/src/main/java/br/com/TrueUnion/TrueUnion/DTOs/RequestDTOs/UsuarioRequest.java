package br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs;

import br.com.TrueUnion.TrueUnion.Entities.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class UsuarioRequest {

	@NotBlank(message = "nome é obrigatorio!")
	private String nome;
	@NotBlank(message = "cpf é obrigatorio!")
	private String cpf;
	@NotBlank(message = "email é obrigatorio!")
	@Email(message = "valide se o email segue o padrão solicitado!")
	private String email;
	private String telefone;
	private String senha;

	public UsuarioRequest(String nome, String cpf, String email, String telefone, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
