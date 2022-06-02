package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Reposioty.UsuarioRepository;

@Controller
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




}
