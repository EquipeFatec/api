package sanjavaley.heyalexia.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "usu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usu_nome")
    private String nome;

    @Column(name = "usu_senha")
    private String senha;

    @Column(name = "usu_email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "uau_usuario_autorizacao",
            joinColumns = { @JoinColumn(name = "usu_id")},
            inverseJoinColumns = { @JoinColumn(name = "aut_id") }
    )
    private Set<Autorizacao> autorizacoes;

    public Usuario(String nome, String senha, Set<Autorizacao> autorizacoes, String email) {
    }
}
