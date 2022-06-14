package sanjavaley.heyalexia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sanjavaley.heyalexia.Entity.TipoProduto;
import sanjavaley.heyalexia.Service.TipoProdutoServiceImp;

@RestController
@RequestMapping(value = "/produtotipo") //Rota
public class TipoProdutoController {

	//instancia
	@Autowired
	private TipoProdutoServiceImp service;
	
	//busca todos os tipos de produto
	@GetMapping(value = "/todos")
    public List<TipoProduto> GetAllTipoProduto() {
        return service.getAllTipoProduto();        
	}

	//busca tipo por ide retorna obj de produto
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoProduto> findById(@PathVariable long id) {		
		TipoProduto obj = service.BuscaPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	

	
	
}
