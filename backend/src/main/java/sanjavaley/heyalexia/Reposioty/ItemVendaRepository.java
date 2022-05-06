package sanjavaley.heyalexia.Reposioty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sanjavaley.heyalexia.Entity.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
	
	@Query(nativeQuery = true ,value = "select  p.prod_nome, sum(it.itv_quantidade) as soma, sum(it.itv_valor) as valor_total "
			+ " from item_venda it join produto p on p.prod_id = it.prod_id "
			+ " group by p.prod_nome order by soma desc,valor_total desc FETCH FIRST 5 ROWS ONLY")
	ArrayList<String[]> topSell();

	@Query(nativeQuery = true, value = "SELECT SUM(ITV_QUANTIDADE) from item_venda")
	String valorTotal();
}
