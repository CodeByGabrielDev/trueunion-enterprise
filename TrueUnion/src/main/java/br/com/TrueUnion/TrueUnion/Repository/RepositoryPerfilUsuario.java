package br.com.TrueUnion.TrueUnion.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.TrueUnion.TrueUnion.Entities.PerfilUsuario;

public interface RepositoryPerfilUsuario extends CrudRepository<PerfilUsuario, Integer> {
	
	@Query("SELECT E FROM PerfilUsuario E WHERE E.descProgramador = :descProgramador")
	PerfilUsuario findByParametro(String descProgramador); 
}
