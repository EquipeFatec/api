package sanjavaley.heyalexia.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Table(name="ENDERECO") //tabela do banco associada
public class Endereco {
	
	
	@Id
    @GeneratedValue //gera sequencia do id
	@Column(name = "ENDERECO_ID")
	private long id;
	
	@Column(name = "LOGADOURO", length = 100, nullable = false)
	private String logadouro;
	
	@ManyToOne //um bairro tem varios endereços
	@JoinColumn(name = "BAIRRO_ID")
	private Bairro bairroId;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "endereco") //varios clientes podem ter um endereço
	private List<Cliente> cliente;

}
