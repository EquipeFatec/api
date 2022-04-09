package fatec.sanjavalley.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Float valor;
    private TipoProdutoDTO tipoProdutoDTO;
}
