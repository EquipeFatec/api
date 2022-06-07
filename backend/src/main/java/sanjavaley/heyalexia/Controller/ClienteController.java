package sanjavaley.heyalexia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sanjavaley.heyalexia.Entity.Cliente;
import sanjavaley.heyalexia.Service.ClienteServiceImp;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/cliente")
public class ClienteController {

	
	@Autowired
	private ClienteServiceImp service;
	
	@GetMapping(value = "/{nome}")
	public ResponseEntity<List<Cliente>> findByNome(@PathVariable String nome){
		try {
		List<Cliente> list = service.findByNome(nome);
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping(value = "/genero")
	public List<Integer> contaGenero(){
		List<Integer> genero = service.genero();
		return genero;
		
	}
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<Cliente>> buscaTodos(){
		List<Cliente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/apaga")
	public void apagaTodos() {
		service.apagaTodos();
	}

	@GetMapping(value = "/idade")
	public ResponseEntity<List<String>> orderByIdade(){
		List<String> cliente = service.orderByIdade();
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping(value = "/cidade")
	public ResponseEntity<List<String>> orderByCidade(){
		List<String> cliente = service.orderByCidade();
		return ResponseEntity.ok().body(cliente);
	}
}
