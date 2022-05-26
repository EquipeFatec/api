package sanjavaley.heyalexia.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Service.SegurancaServiceImp;

import java.util.List;

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
