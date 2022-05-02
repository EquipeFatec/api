package fatec.sanjavalley.backend.controller;

import fatec.sanjavalley.backend.dto.ProdutoDTO;
import fatec.sanjavalley.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> buscarTodosProdutos(){
       return produtoService.convertToDTOList(produtoService.buscarTodosProdutos());
    }

}
