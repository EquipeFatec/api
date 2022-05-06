package sanjavaley.heyalexia.Reposioty;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import sanjavaley.heyalexia.Entity.Venda;


public interface VendaRepository extends JpaRepository<Venda, Long>{

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM VENDA")
    String vendaTotal();

}
