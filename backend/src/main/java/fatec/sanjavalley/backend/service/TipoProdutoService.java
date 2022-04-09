package fatec.sanjavalley.backend.service;

import fatec.sanjavalley.backend.dto.TipoProdutoDTO;
import fatec.sanjavalley.backend.entity.TipoProduto;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoService {

    public TipoProduto convertToEntity(TipoProdutoDTO tipoProdutoDTO){
        return TipoProduto
                .builder()
                .id(tipoProdutoDTO.getId())
                .nome(tipoProdutoDTO.getNome())
                .build();
    }

    public TipoProdutoDTO convertToDTO(TipoProduto tipoProduto){
        return TipoProdutoDTO
                .builder()
                .id(tipoProduto.getId())
                .nome(tipoProduto.getNome())
                .build();
    }

}
