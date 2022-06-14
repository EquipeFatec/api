package sanjavaley.heyalexia.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sanjavaley.heyalexia.Security.JwtUtils;
import sanjavaley.heyalexia.Security.Login;


@RestController
@RequestMapping(value = "/login") //Rota
@CrossOrigin(origins = "http://localhost:8080") //Libera a porta para o front acessar
public class LoginController {

    //instancia
    @Autowired
    private AuthenticationManager authManager;

    //envia objeto de login, retorna login realizado
    @PostMapping()
    public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
        Authentication auth = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        auth = authManager.authenticate(auth);
        login.setPassword(null);
        login.setToken(JwtUtils.generateToken(auth));
        //login.setAutorizacao(auth.getAuthorities().iterator().next().getAuthority());
        return login;
    }
}
