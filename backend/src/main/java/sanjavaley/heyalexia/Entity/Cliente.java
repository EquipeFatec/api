package sanjavaley.heyalexia.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="CLIENTE")
public class Cliente {
			
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
	 
	 //@JsonIgnore
	 @ManyToOne//fetch = FetchType.LAZY)
	 @JoinColumn(name = "ENDERECO_ID")
	 private Endereco endereco;

	 @Column(name = "CLIENTE_NASCIMENTO")
	private String ClienteNascimento;

	/*public Long getIdade() throws ParseException {
		Date date = Calendar.getInstance().getTime();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		//String data = getClienteNascimento();
		//Date dataFormatada = format.parse(data);
		long diff = (date.getTime() - getClienteNascimento().getTime());
		long diffyears = TimeUnit.MILLISECONDS.toDays(diff) / 365;
		return diffyears;
	}*/
}
