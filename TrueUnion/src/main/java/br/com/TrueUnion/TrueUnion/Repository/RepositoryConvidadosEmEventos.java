package br.com.TrueUnion.TrueUnion.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.TrueUnion.TrueUnion.Entities.ConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryConvidadosEmEventos extends CrudRepository<ConvidadosEmEventos, Integer> {

	@Query("SELECT C.evento FROM ConvidadosEmEventos C WHERE C.convidado = :usuario AND C.status.id =1 ")
	Iterable<Evento> eventosDisponiveisParaVerificar(Usuario usuario);

	@Query("SELECT C FROM ConvidadosEmEventos C WHERE C.evento = :evento AND C.convidado = :usuario")
	ConvidadosEmEventos puxarLinhaConvidadosEmEventos(Evento evento, Usuario usuario);

}
