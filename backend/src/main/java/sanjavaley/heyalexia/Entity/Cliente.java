package sanjavaley.heyalexia.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Table(name="CLIENTE") //tabela do banco associada
public class Cliente {
			
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) //gera sequencia do id
	 private Long Cliente_Id;
	
	 @Column(name="CLIENTE_NOME")
	 private String Nome;
	 
	 @Column(name="CLIENTE_SOBRENOME")
	 private String Sobrenome;
	
	 @Column(name="CLIENTE_TELEFONE")
	 private String ClienteTelefone;
	 
	 @Column(name="CLIENTE_EMAIL")
	 private String ClienteEmail;
	 
	 @Column(name="CLIENTE_GENERO")
	 private String ClienteGenero;

	@Column(name="CLIENTE_NASCIMENTO")
	private String ClienteNascimento;
	 
	 //@JsonIgnore
	 @ManyToOne//fetch = FetchType.LAZY) //muitos clientes podem ter um endereço
	 @JoinColumn(name = "ENDERECO_ID")
	 private Endereco endereco;

	 public Long getIdade() throws ParseException { //calcula idade
		 Date date = new Date();
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 String data = ClienteNascimento;
		 Date dataFormatada = format.parse(data);
		 long diff = (date.getTime() - dataFormatada.getTime());
		 return TimeUnit.MILLISECONDS.toDays(diff) / 365;
	 }
}
