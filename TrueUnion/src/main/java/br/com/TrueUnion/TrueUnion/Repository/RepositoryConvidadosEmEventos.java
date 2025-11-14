package br.com.TrueUnion.TrueUnion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.TrueUnion.TrueUnion.Entities.ConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryConvidadosEmEventos extends CrudRepository<ConvidadosEmEventos, Integer> {
	@Query("SELECT E FROM ConvidadosEmEventos E WHERE E.evento = :evento AND E.convidado = :usuario")
	ConvidadosEmEventos findEvento(@Param("evento ") Evento evento, @Param("usuario") Usuario usuario);

	@Query("SELECT C FROM ConvidadosEmEventos C WHERE C.evento = :evento AND C.convidado = :usuario")
	ConvidadosEmEventos puxarLinhaConvidadosEmEventos(Evento evento, Usuario usuario);

	@Query("SELECT E FROM ConvidadosEmEventos E WHERE E.evento = :evento AND E.status.id_RSVP = 1")
	List<ConvidadosEmEventos> findQuantidadeDeLinhasPendentes(@Param("evento") Evento evento);

	@Query("SELECT E FROM ConvidadosEmEventos E WHERE E.evento = :evento AND E.status.id_RSVP = 3")
	List<ConvidadosEmEventos> findQuantidadeDeLinhasAceitas(@Param("evento") Evento evento);

	@Query("SELECT E FROM ConvidadosEmEventos E WHERE E.evento = :evento AND E.status.id_RSVP = 2")
	List<ConvidadosEmEventos> findQuantidadeDeLinhasRecusadas(@Param("evento") Evento evento);
}
