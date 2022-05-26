package sanjavaley.heyalexia.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanjavaley.heyalexia.Entity.Autorizacao;
import sanjavaley.heyalexia.Entity.Usuario;
import sanjavaley.heyalexia.Reposioty.SegurancaRepository;
import sanjavaley.heyalexia.Reposioty.UsuarioRepository;

@Service
public class SegurancaServiceImp implements SegurancaRepository {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario cadastrarUsuario(Usuario usuario) {
        if(usuario.getNome() == null || usuario.getSenha() == null
                || usuario.getNome().length() < 3 || usuario.getSenha().length() < 3) {
            throw new IllegalArgumentException("Nome ou senha inválidos");
        }
        return usuarioRepo.save(usuario);
    }

    public Usuario cadastrarUsuario(String nome, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        return cadastrarUsuario(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    public Usuario buscaUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(id);
        if(usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }
        else {
            throw new NullPointerException("Usuario não existe");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.getByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return User.builder().username(username).password(usuario.getSenha())
                .authorities(usuario.getAutorizacoes().stream()
                        .map(Autorizacao::getNome).collect(Collectors.toList())
                        .toArray(new String[usuario.getAutorizacoes().size()]))
                .build();
    }
}
