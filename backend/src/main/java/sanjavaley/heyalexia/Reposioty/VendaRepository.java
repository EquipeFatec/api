package sanjavaley.heyalexia.Reposioty;

import oracle.sql.DATE;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import sanjavaley.heyalexia.Entity.Venda;

import java.util.List;

//repository = define os metodos q irao acessar o banco
public interface VendaRepository extends JpaRepository<Venda, Long>{

    //calcula total venda por mes
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VENDA where EXTRACT (MONTH from venda_data) = ?1")
    String vendaTotal(Integer mes);

    //total venda
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VENDA")
    String vendaTotal();

    //total venda por intervalo de data
    @Query(nativeQuery = true, value = "select sum(itv.itv_valor) from venda v join item_venda itv on itv.venda_id = v.venda_id " +
            " where (venda_data > TO_DATE(?1) and venda_data < TO_DATE(?2))")
    String valorTotalPorData(String _dataInicial, String _dataFinal);

    //valor por mes e ano
    @Query(nativeQuery = true, value = " select sum(itv.itv_valor) as \"VALOR\", " +
            " extract(year from venda_data) as \"ANO\", " +
            " extract(month from venda_data) as \"MES\" " +
            " from venda v " +
            " join item_venda itv on itv.venda_id = v.venda_id " +
            " group by extract(year from venda_data), extract(month from venda_data) " +
            " order by extract(year from venda_data), " +
            " extract(month from venda_data)")
    List<Object> valoresPorMesAno();
}
