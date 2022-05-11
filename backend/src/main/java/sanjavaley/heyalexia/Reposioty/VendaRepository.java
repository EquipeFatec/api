package sanjavaley.heyalexia.Reposioty;

import oracle.sql.DATE;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import sanjavaley.heyalexia.Entity.Venda;


public interface VendaRepository extends JpaRepository<Venda, Long>{

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VENDA where EXTRACT (MONTH from venda_data) = ?1")
    String vendaTotal(Integer mes);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VENDA")
    String vendaTotal();

    @Query(nativeQuery = true, value = "select sum(itv.itv_valor) from venda v join item_venda itv on itv.venda_id = v.venda_id " +
            " where (venda_data > ?1 and venda_data < ?2)")
    String valorTotalPorData(String _dataInicial, String _dataFinal);

}
