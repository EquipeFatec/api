package sanjavaley.heyalexia.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Table(name="TIPO_PAGAMENTO") //tabela do banco associada
public class TipoPagamento {
	
	@Id
    @GeneratedValue //gera sequencia do id
	@Column(name = "TIPO_PAG_ID")
	private long id;
	
	@Column(name = "TIPO_PAG_NOME", length = 100, nullable = false)
	private String tipoPagamentoNome;

}
