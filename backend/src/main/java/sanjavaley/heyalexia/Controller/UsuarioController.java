package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sanjavaley.heyalexia.Email.SpringEmailMain;
import sanjavaley.heyalexia.Entity.Email;
import sanjavaley.heyalexia.Entity.TipoProduto;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Reposioty.UsuarioRepository;

import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/cadastro")
public class UsuarioController {

    @Autowired
    private UsuarioRepository uRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        try{
            Usuario _usuario = uRepository
                    .save(new Usuario(usuario.getNome(), encoder.encode(usuario.getSenha()),
                    usuario.getAutorizacoes(), usuario.getEmail()));
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findbyemail/{email}")
    public ResponseEntity<Usuario> findUsuarioByEmail(@PathVariable("email") String email){
        try{
            Optional<Usuario> found = uRepository.findByEmail(email);
            return found.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Usuario> editUsuario(@RequestBody Usuario usuario){
        try{
            Optional<Usuario> toEdit = uRepository.findById(usuario.getId());
            if(toEdit.isPresent()){
                Usuario save = new Usuario();
                save.setId(usuario.getId());
                save.setEmail(usuario.getEmail());
                save.setNome(usuario.getNome());

                save.setSenha(!Objects.equals(usuario.getSenha(), toEdit.get().getSenha()) ?
                        encoder.encode(usuario.getSenha()) : usuario.getSenha());

                uRepository.save(save);
                return new ResponseEntity<>(save, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/email")
    public void send(@RequestBody Email email){
        SpringEmailMain.main(email.getEmail(), email.getAssunto(), email.getCorpo());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id) {
        Usuario obj = uRepository.getById(id);
        return ResponseEntity.ok().body(obj);
    }





}
