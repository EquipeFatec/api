package sanjavaley.heyalexia.Controller;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import sanjavaley.heyalexia.Entity.Cliente;
import sanjavaley.heyalexia.Entity.Endereco;
import sanjavaley.heyalexia.Reposioty.ClienteRepository;
import sanjavaley.heyalexia.Reposioty.EnderecoRepository;



@Controller
@CrossOrigin(origins = "http://localhost:8080") //Libera a porta para o front acessar
@RequestMapping(value = "/upload") //Rota
public class CSVController {
	
	//instancia
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EnderecoRepository eRepository;

	//envia arquivo para salvar
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> save(@RequestParam("file") MultipartFile file) 
throws Exception{
		List<Cliente> cliente = new ArrayList<>();
		InputStream IStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		
		List<Record> parseAllRecord = parser.parseAllRecords(IStream);
		parseAllRecord.forEach(record ->{
			Cliente clientes = new Cliente();
			Endereco end = eRepository.findById(Long.parseLong(record.getString("Endereco_Id")));
			clientes.setNome(record.getString("Nome"));
			clientes.setSobrenome(record.getString("Sobrenome"));			
			clientes.setClienteEmail(record.getString("ClienteEmail"));
			clientes.setClienteTelefone(record.getString("ClienteTelefone"));
			clientes.setClienteGenero(record.getString("Genero"));
			clientes.setClienteNascimento(record.getString("Cliente_Nascimento"));
			clientes.setEndereco(end);


			cliente.add(clientes);				
		}); //para cada registro pega as colunas do CSV e adiciona na lista de cliente
		repository.saveAll(cliente); //salva no repository
		return new ResponseEntity<Cliente>(HttpStatus.CREATED); //retorna que foi criado
	}
}