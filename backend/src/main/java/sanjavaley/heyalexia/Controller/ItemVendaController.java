package sanjavaley.heyalexia.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sanjavaley.heyalexia.Entity.ItemVenda;
import sanjavaley.heyalexia.Service.ItemVendaServiceImp;

@RestController
@CrossOrigin(origins = "http://localhost:8080") //Libera a porta para o front acessar
@RequestMapping(value = "/item-venda") //Rota
public class ItemVendaController {
	
	
	//instancia
	@Autowired
	private ItemVendaServiceImp service;
	
	//busca top 5 mais vendidos, retorna como array
	@GetMapping(value = "/top")
	public ArrayList<String[]> topSellOrderByQuantidade(){		
		ArrayList<String[]> lista = new ArrayList<>();

		lista.addAll(service.topSell()); //adiciona os itens mais vendidos na lista
		return lista;


	}

}
