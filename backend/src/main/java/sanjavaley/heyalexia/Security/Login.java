package sanjavaley.heyalexia.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Login {

    private String username;

    private String password;

    private String autorizacao;

    private String token;
}
