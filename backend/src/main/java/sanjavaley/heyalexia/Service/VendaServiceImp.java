package sanjavaley.heyalexia.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sanjavaley.heyalexia.Reposioty.VendaRepository;


@Service
public class VendaServiceImp  {

	
	@Autowired
	private VendaRepository repository;

	public String vendaTotal(){
		String total = repository.vendaTotal();
		return total;
	}
}
