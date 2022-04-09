package fatec.sanjavalley.backend.repository;

import fatec.sanjavalley.backend.entity.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
}
