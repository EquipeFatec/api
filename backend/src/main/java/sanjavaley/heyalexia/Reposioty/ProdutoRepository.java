package sanjavaley.heyalexia.Reposioty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sanjavaley.heyalexia.Entity.Produto;

//repository = define os metodos q irao acessar o banco
@Repository
public interface ProdutoRepository 
extends JpaRepository<Produto, Long> {

}
