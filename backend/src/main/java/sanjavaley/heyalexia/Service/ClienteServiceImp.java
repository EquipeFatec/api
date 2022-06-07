package sanjavaley.heyalexia.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sanjavaley.heyalexia.Entity.Cliente;
import sanjavaley.heyalexia.Reposioty.ClienteRepository;

@Service
public class ClienteServiceImp {

	@Autowired
	private ClienteRepository repository;


	
	public List<Cliente> findByNome(String _nome) {
		Optional<List<Cliente>> list = repository.findByNome(_nome);	
		return list.orElse(null);
		
	}
		
	public List<Integer> genero(){
		List<Integer> genero = repository.contadorGenero();
		return genero;
	}

	public List<Cliente> findAll() {
		List<Cliente> list = repository.findAll();
		return list;
	}
	
	public void apagaTodos() {
		repository.deleteAll();
	}
	
	public List<String> orderByIdade(){
		List<String> list = repository.orderByIdade();
		return list;
	}

	public List<String> orderByCidade(){
		List<String> list = repository.orderByCidade();
		return list;
	}


}
