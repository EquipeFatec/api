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
@CrossOrigin(origins = "http://localhost:8080") //Libera a porta para o front acessar
@RequestMapping(value = "/cadastro") //rota
public class UsuarioController {

    //instancia
    @Autowired
    private UsuarioRepository uRepository;

    @Autowired
    private PasswordEncoder encoder;

    //envia obj do usuario para criar
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        try{
            Usuario _usuario = uRepository
                    .save(new Usuario(usuario.getNome(), encoder.encode(usuario.getSenha()),
                    usuario.getAutorizacoes(), usuario.getEmail())); //enconder criptografa senha
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //busca usuario passando email como parametro e retorna obj do usuario
    @GetMapping("/findbyemail/{email}")
    public ResponseEntity<Usuario> findUsuarioByEmail(@PathVariable("email") String email){
        try{
            Optional<Usuario> found = uRepository.findByEmail(email);
            //retorna usuario, se nao encontrar retorna not found
            return found.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //edita usuario passando obj como parametro e retornando o obj do usuario editado
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
                        encoder.encode(usuario.getSenha()) : usuario.getSenha()); //verifica se a senha foi alterada, se foi criptografa a nova senha

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

    //envia obj do email para fazer o envio
    @PostMapping(value = "/email")
    public void send(@RequestBody Email email){
        SpringEmailMain.main(email.getEmail(), email.getAssunto(), email.getCorpo());
    }

    //busca usuario passando id como parametro e retorna obj do usuario
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id) {
        Usuario obj = uRepository.getById(id);
        return ResponseEntity.ok().body(obj);
    }





}
