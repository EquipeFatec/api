package sanjavaley.heyalexia.Reposioty;

import org.springframework.data.jpa.repository.JpaRepository;
import sanjavaley.heyalexia.Entity.Autorizacao;

//repository = define os metodos q irao acessar o banco
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
}
