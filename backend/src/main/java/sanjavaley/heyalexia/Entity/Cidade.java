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
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Table(name="CIDADE") //tabela do banco associada
public class Cidade {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera sequencia do id
	@Column(name="CIDADE_ID")
	 private long id;
	
	 @Column(name="CIDADE_NOME")
	 private String Nome;
	 
	 @ManyToOne //um estado pode ter muitas cidades
	    @JoinColumn(name="EST_ID")
	    private Estado estadoId;
}
