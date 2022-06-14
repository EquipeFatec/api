package sanjavaley.heyalexia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sanjavaley.heyalexia.Entity.Produto;
import sanjavaley.heyalexia.Reposioty.ProdutoRepository;

//service = onde ficam as regras de negocio e as logicas
@Service
public class ProdutoServiceImp  {

	@Autowired
	private ProdutoRepository repositorioProd;
	
	
	public void excluirProduto(long id) {
		repositorioProd.deleteById(id);
	}
	
	public void selecionarProduto(long id) {
		repositorioProd.findById(id);
		
	}
	
	public void criarProduto(long id) {
		repositorioProd.save(null);
		
	}	
	
	public List<Produto> getAllProduto() {
		return repositorioProd.findAll();
	}
}
