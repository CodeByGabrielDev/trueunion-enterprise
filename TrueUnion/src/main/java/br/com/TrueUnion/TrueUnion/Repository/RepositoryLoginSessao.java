package br.com.TrueUnion.TrueUnion.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.TrueUnion.TrueUnion.Entities.LoginSessao;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

@Repository
public interface RepositoryLoginSessao extends JpaRepository<LoginSessao, Long> {
	Optional<LoginSessao> findByToken(String token);

	void deleteByUsuario(Usuario usuario);

	@Query("SELECT E.token FROM LoginSessao E WHERE E.usuario = :usuario")
	Optional<String> takeTokenByIdUserInTableLoginSessao(Usuario usuario);

	@Query("SELECT E.usuario FROM LoginSessao E WHERE E.token = :token")
	Optional<Usuario> findUsuarioByToken(String token);

	@Modifying
	@Query("DELETE FROM LoginSessao E where E.token = :token")
	void deleteByToken(String token);
}
