package br.com.TrueUnion.TrueUnion.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryEvento extends CrudRepository<Evento, Integer> {

	@Query("SELECT E FROM Evento E WHERE E.donoEvento = :usuario")
	Iterable<Evento> findEventoByDono(Usuario usuario);

	@Query("SELECT E FROM Evento E WHERE E.dataFim < :dataAtual")
	Iterable<Evento> findEventosConcluidos(@Param("dataAtual") LocalDate dataAtual);
	@Query("SELECT E.evento FROM ConvidadosEmEventos E WHERE E.convidado = :usuario")
	List<Evento> findEventosQueFuiConvidado(@Param("usuario")Usuario usuario);
	
	
}
