package sanjavaley.heyalexia.Reposioty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sanjavaley.heyalexia.Entity.Cliente;

import javax.persistence.Tuple;

//repository = define os metodos q irao acessar o banco
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	

		@Query("select c from Cliente c where c.Nome like ?1%")
		Optional<List<Cliente>> findByNome(String nome);


		
		@Query(nativeQuery = true, value = "select count(cliente_id) as contador from cliente group by cliente_genero")
		List<Integer> contadorGenero();

		@Query(nativeQuery = true, value = "select teste, count(*), cliente_genero from " +
				"(select (case when trunc((months_between(sysdate, to_date(cliente_nascimento)))/12) < 20 then 0 " +
				"when trunc((months_between(sysdate, to_date(cliente_nascimento)))/12) < 30 then 1 " +
				"when trunc((months_between(sysdate, to_date(cliente_nascimento)))/12) < 45 then 2 " +
				"when trunc((months_between(sysdate, to_date(cliente_nascimento)))/12) < 60 then 3 " +
				"else 4 end) as teste, cliente_genero from cliente ) group by teste, cliente_genero")
		List<String> orderByIdade();

		@Query(nativeQuery = true, value = "select c.cliente_genero, cid.cidade_nome, count(*) from cliente c join endereco e on c.endereco_id = e.endereco_id " +
				"join bairro b on b.bairro_id = e.bairro_id " +
				"join cidade cid on cid.cidade_id = b.cidade_id " +
				"group by c.cliente_genero,cid.cidade_nome")
		List<String> orderByCidade();
	
}
