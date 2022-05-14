package sanjavaley.heyalexia.Service;

import oracle.sql.DATE;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sanjavaley.heyalexia.Reposioty.VendaRepository;

import java.util.List;


@Service
public class VendaServiceImp  {

	@Autowired
	private VendaRepository repository;

	public String vendaTotal(Integer mes){
		String total;
		if(mes == null){
			total = repository.vendaTotal();
		}else{
			total = repository.vendaTotal(mes);
		}
		return total;
	}

	public String valorVendaPorData(String _dataInicial, String _dataFinal){
		String total = repository.valorTotalPorData(_dataInicial,_dataFinal);
		return total;
	}

	public List<Object> valoresPorMesAno(){
		return repository.valoresPorMesAno();
	}
}
