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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


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

	@Column(name="CLIENTE_NASCIMENTO")
	private String ClienteNascimento;
	 
	 //@JsonIgnore
	 @ManyToOne//fetch = FetchType.LAZY)
	 @JoinColumn(name = "ENDERECO_ID")
	 private Endereco endereco;

	 public Long getIdade() throws ParseException {
		 Date date = new Date();
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 String data = ClienteNascimento;
		 Date dataFormatada = format.parse(data);
		 long diff = (date.getTime() - dataFormatada.getTime());
		 return TimeUnit.MILLISECONDS.toDays(diff) / 365;
	 }


}