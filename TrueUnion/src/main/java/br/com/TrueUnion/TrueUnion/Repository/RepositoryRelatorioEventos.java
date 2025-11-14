package br.com.TrueUnion.TrueUnion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.RelatorioDespesasEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryRelatorioEventos extends CrudRepository<RelatorioDespesasEmEventos, Integer> {

	@Query("SELECT E FROM RelatorioDespesasEmEventos E WHERE E.eventoVinculado = :evento "
			+ "AND E.eventoVinculado.donoEvento = :pessoa")
	List<RelatorioDespesasEmEventos> searchRelatorioByIdEvento(@Param("evento") Evento evento,
			@Param("pessoa") Usuario pessoa);
}
