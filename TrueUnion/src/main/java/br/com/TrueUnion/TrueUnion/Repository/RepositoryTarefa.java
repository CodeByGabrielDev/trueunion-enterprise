package br.com.TrueUnion.TrueUnion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Tarefa;

public interface RepositoryTarefa extends CrudRepository<Tarefa, Integer> {

	@Query("SELECT T FROM Tarefa T WHERE T.evento = :evento")
	List<Tarefa> findTarefasByEvento(@Param("evento") Evento evento);

}
