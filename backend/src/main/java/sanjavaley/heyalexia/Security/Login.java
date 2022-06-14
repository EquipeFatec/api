package sanjavaley.heyalexia.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Objeto do login
@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
public class Login {

    private String email;

    private String password;

    private String autorizacao;

    private String token;

}
