package sanjavaley.heyalexia.Reposioty;

import org.springframework.data.jpa.repository.JpaRepository;

import sanjavaley.heyalexia.Entity.Endereco;

//repository = define os metodos q irao acessar o banco
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	Endereco findById(long id);
}
