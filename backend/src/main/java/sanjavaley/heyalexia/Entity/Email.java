package sanjavaley.heyalexia.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getters e setters
@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
public class Email {

    private String email;

    private String assunto;

    private String corpo;
}
