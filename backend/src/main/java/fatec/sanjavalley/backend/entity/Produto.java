package fatec.sanjavalley.backend.entity;

import fatec.sanjavalley.backend.dto.ProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue
    @Column(name = "PROD_ID")
    private Long id;

    @Column(name = "PROD_NOME")
    private String nome;

    @Column(name = "PROD_VALOR")
    private Float valor;

    @ManyToOne
    @JoinColumn(name="TP_ID")
    private TipoProduto tipoProduto;
}
