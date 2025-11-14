package br.com.TrueUnion.TrueUnion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.TrueUnion.TrueUnion.Entities.Pagamento;
import br.com.TrueUnion.TrueUnion.Entities.ParcelamentoPagamento;

public interface RepositoryParcelasPagamento extends CrudRepository<ParcelamentoPagamento, Integer> {

	@Query("SELECT E FROM ParcelamentoPagamento E WHERE E.pagamento = :pagamento AND E.paga = True")
	List<ParcelamentoPagamento> buscarParcelasPagas(@Param("pagamento") Pagamento pagamento);
}
