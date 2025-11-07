package br.com.TrueUnion.TrueUnion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.TrueUnion.TrueUnion.Entities.Despesas;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryDespesa extends CrudRepository<Despesas, Integer> {
	
	@Query("SELECT E FROM Despesas E WHERE E.event.donoEvento = :usuario")
	List<Despesas> findDespesasVinculadosAoUsuario(@Param("usuario") Usuario usuario);
}
