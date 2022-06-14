package sanjavaley.heyalexia.Entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


@Data //getters e setters
@Builder //ajuda a montar o obj
@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Entity //anotação para especificar q é entidade
@Table(name = "PRODUTO") //tabela do banco associada
public class Produto {
	
	@Id
    @GeneratedValue //gera sequencia do id
	@Column(name = "PROD_ID")
	private long id;
	
	@Column(name = "PROD_NOME", unique=true, length = 100, nullable = false)
	private String nomeProduto;
	
	@Column(name = "PROD_VALOR", unique=false, length = 7, nullable = false)
	private long valor;
	
	@ManyToOne //muitos produtos podem estar associados a um tipo
    @JoinColumn(name="TP_ID")
    private TipoProduto tipoProduto;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto") //um item de venda pode ter muitos pdodutos
	private List<ItemVenda> itemVenda;
	
	
	
	
	
	
	

	
}
