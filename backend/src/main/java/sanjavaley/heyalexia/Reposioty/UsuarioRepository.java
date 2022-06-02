package sanjavaley.heyalexia.Reposioty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sanjavaley.heyalexia.Entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario getByNome(String nome);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);

    Usuario getByNomeAndSenha(String nome, String senha);

    List<Usuario> getByNomeContainsIgnoreCase(String nome);

    List<Usuario> getByIdGreaterThan(Long id);

    @Query("select u from Usuario u where u.nome = ?1")
    Usuario buscarPorNome(String nome);

    List<Usuario> getByAutorizacoesNome(String autorizacao);

    @Query("select u from Usuario u join u.autorizacoes a where a.nome = ?1")
    List<Usuario> buscarUsuarioPorAutorizacao(String autorizacao);

}
