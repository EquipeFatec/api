package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sanjavaley.heyalexia.Email.SpringEmailMain;
import sanjavaley.heyalexia.Entity.Email;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Reposioty.UsuarioRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/cadastro")
public class UsuarioController {

    @Autowired
    private UsuarioRepository uRepository;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        try{
            Usuario _usuario = uRepository
                    .save(new Usuario(usuario.getNome(), usuario.getSenha()
                    , usuario.getAutorizacoes(), usuario.getEmail()));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/email")
    public void send(@RequestBody Email email){
        SpringEmailMain.main(email.getEmail(), email.getAssunto(), email.getCorpo());
    }



}
