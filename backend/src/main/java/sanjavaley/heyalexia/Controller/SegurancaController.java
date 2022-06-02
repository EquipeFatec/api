package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Service.SegurancaServiceImp;

import java.util.List;


@RestController
@CrossOrigin
public class SegurancaController {

    @Autowired
    private SegurancaServiceImp segurancaService;

    @GetMapping
    public List<Usuario> listarTodosUsuario() {
        return segurancaService.listarUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario buscaUsuarioPorId(@PathVariable("id") Long id) {
        return segurancaService.buscaUsuarioPorId(id);
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return segurancaService.cadastrarUsuario(usuario);
    }
}
