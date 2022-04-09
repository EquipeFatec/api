package fatec.sanjavalley.backend.service;

import fatec.sanjavalley.backend.dto.ProdutoDTO;
import fatec.sanjavalley.backend.entity.Produto;
import fatec.sanjavalley.backend.repository.ProdutoRepository;
import fatec.sanjavalley.backend.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    TipoProdutoService tipoProdutoService;

    public Produto convertToEntity(ProdutoDTO produtoDTO){
        return Produto
                .builder()
                .id(produtoDTO.getId())
                .nome(produtoDTO.getNome())
                .valor(produtoDTO.getValor())
                .tipoProduto(tipoProdutoService.convertToEntity(
                        produtoDTO.getTipoProdutoDTO()))
                .build();
    }

    public ProdutoDTO convertToDTO(Produto produto){
        return ProdutoDTO
                .builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .tipoProdutoDTO(tipoProdutoService.convertToDTO(
                        produto.getTipoProduto()))
                .build();
    }

    public List<ProdutoDTO> convertToDTOList(List<Produto> produtoList){
        return produtoList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }
}
