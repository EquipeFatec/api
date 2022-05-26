package sanjavaley.heyalexia.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sanjavaley.heyalexia.Entity.ItemVenda;
import sanjavaley.heyalexia.Reposioty.ItemVendaRepository;

@Service
public class ItemVendaServiceImp {

	
	@Autowired
	private ItemVendaRepository repos;
	
	
	public ArrayList<String[]> topSell(){
		ArrayList<String[]> list = repos.topSell();

		return list;
	}
	
	public String valorTotal(Integer mes){
		String valor;
		if(mes == null){
			valor = repos.valorTotal();
		}else{
			valor = repos.valorTotal(mes);
		}
		return valor;
	}
}
