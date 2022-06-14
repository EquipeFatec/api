package sanjavaley.heyalexia.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Builder //ajuda a montar o objeto
@Table(name = "ITEM_VENDA") //tabela do banco associada
public class ItemVenda {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	//gera sequencia do id
	@Column(name = "itv_id")
	private long id;
	
	@Column(name = "ITV_QUANTIDADE")
	private long quantidade;
	
	@Column(name = "ITV_VALOR")
	private long valor;
	
	@ManyToOne //um produto pode estar em muitos itens de venda
	@JoinColumn(name = "PROD_ID")
	private Produto produto;
	
	@ManyToOne //uma venda pode ter muitos itens de venda
	@JoinColumn(name = "VENDA_ID")
	private Venda venda;

	private Long porcentagemVenda(){
		long soma =+ quantidade;
		long p = (quantidade / soma) * 100;
		return p;
	}
	

	
}