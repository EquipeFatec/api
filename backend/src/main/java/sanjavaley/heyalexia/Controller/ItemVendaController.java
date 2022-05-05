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
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/item-venda")
public class ItemVendaController {
	
	
	
	@Autowired
	private ItemVendaServiceImp service;
	
	
	@GetMapping(value = "/top")
	public ArrayList<String[]> topSellOrderByQuantidade(){		
		ArrayList<String[]> lista = new ArrayList<>();
		lista.addAll(service.topSellOrderByQuantidade());		
		return lista;			
	}

}
