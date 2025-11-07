package br.com.TrueUnion.TrueUnion.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Entities.hasherInativador;

public interface RepositoryHasherInativador extends CrudRepository<hasherInativador, Integer> {
	
	@Query("SELECT E.senha FROM hasherInativador E WHERE E.usuarios = :usuarios")
	String pegarSenhaDoUsuarioViaId(Usuario usuarios);

}
