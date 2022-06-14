package sanjavaley.heyalexia.Reposioty;
//para buscar no banco, onde está as query
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sanjavaley.heyalexia.Entity.Usuario;

import java.util.List;
import java.util.Optional;

//repository = define os metodos q irao acessar o banco
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //busca por nome
    Usuario getByNome(String nome);

    //busca por id, optional indica q nao é obrigatorio retornar caso nao encontre
    Optional<Usuario> findById(Long id);

    //busca por emal
    Optional<Usuario> findByEmail(String email);

    //busca por nome
    @Query("select u from Usuario u where u.nome = ?1")
    Usuario buscarPorNome(String nome);

    //busca autorizacoes pelo nome
    List<Usuario> getByAutorizacoesNome(String autorizacao);

    //busca usuarios que tenham uma autorizacao
    @Query("select u from Usuario u join u.autorizacoes a where a.nome = ?1")
    List<Usuario> buscarUsuarioPorAutorizacao(String autorizacao);

}
