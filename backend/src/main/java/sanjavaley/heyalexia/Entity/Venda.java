package sanjavaley.heyalexia.Entity;

import java.sql.Date;
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
public class Venda {
	
	@Id
    @GeneratedValue //gera sequencia do id
	@Column(name = "VENDA_ID")
	private long id;
	
	@ManyToOne //muitas vendas podem estar associadas a um cliente
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente clienteId;
	
	@Column(name = "VENDA_DATA")
	private Date data;

	@ManyToOne //muitas vendas podem ter um tipo de pagamento
	@JoinColumn(name = "VENDA_PAGAMENTO_ID")	
	private TipoPagamento pagamentoId;
	
	@Column(name = "VENDA_DESCONTO")
	private float desconto;
	
	@JsonIgnore
	@OneToMany(mappedBy = "venda") //uma venda pode ter varios itens de venda
	private List<ItemVenda> itemVenda;
	
}
