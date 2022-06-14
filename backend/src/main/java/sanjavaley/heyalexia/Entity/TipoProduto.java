package sanjavaley.heyalexia.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Builder //ajuda a montar o objeto
@Table(name = "TIPO_PRODUTO") //tabela do banco associada
public class TipoProduto {

    @Id
    @GeneratedValue //gera sequencia do id
    @Column(name = "TP_ID")
    private Long id;

    @Column(name = "TP_NOME")
    private String nome;
}

	
		
	
	
	
	
	
	
