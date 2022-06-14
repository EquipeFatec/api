package sanjavaley.heyalexia.Reposioty;

import sanjavaley.heyalexia.Entity.Usuario;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

//repository = define os metodos q irao acessar o banco
public interface SegurancaRepository extends UserDetailsService {

    Usuario cadastrarUsuario(Usuario usuario);

    Usuario cadastrarUsuario(String nome, String senha);

    List<Usuario> listarUsuarios();

    Usuario buscaUsuarioPorId(Long id);
}
