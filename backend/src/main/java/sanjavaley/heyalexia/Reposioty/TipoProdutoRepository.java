package sanjavaley.heyalexia.Reposioty;

import org.springframework.data.jpa.repository.JpaRepository;


import sanjavaley.heyalexia.Entity.TipoProduto;

//repository = define os metodos q irao acessar o banco
public interface TipoProdutoRepository
extends JpaRepository<TipoProduto, Long> {

}
