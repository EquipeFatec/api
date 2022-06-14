package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Reposioty.SegurancaRepository;
import sanjavaley.heyalexia.Service.SegurancaServiceImp;

import java.util.List;


@RestController
@CrossOrigin //Libera a porta para o front acessar
public class SegurancaController {

    //instancia
    @Autowired
    private SegurancaRepository segurancaService;

    //busca e retorna lista com todos os usuarios
    @GetMapping
    public List<Usuario> listarTodosUsuario() {
        return segurancaService.listarUsuarios();
    }

    //busca um usuario passando o ID como parametro e retorna o obj do usuario
    @GetMapping(value = "/{id}")
    public Usuario buscaUsuarioPorId(@PathVariable("id") Long id) {
        return segurancaService.buscaUsuarioPorId(id);
    }

    //envia um obj de usuario pra salvar
    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return segurancaService.cadastrarUsuario(usuario);
    }
}
