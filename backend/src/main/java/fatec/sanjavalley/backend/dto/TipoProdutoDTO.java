package fatec.sanjavalley.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoProdutoDTO {

    private Long id;
    private String nome;
}
