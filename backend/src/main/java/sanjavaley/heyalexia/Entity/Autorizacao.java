package sanjavaley.heyalexia.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor //construtor com todos os argumentos
@NoArgsConstructor //construtor com nenhum argumento
@Data //getters e setters
@Entity //anotação para especificar q é entidade
@Table(name = "autorizacao") //tabela do banco associada
public class Autorizacao {

    @Id
    @Column(name = "aut_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //gera sequencia do id
    private Long id;

    @Column(name = "aut_nome")
    private String nome;

    @ManyToMany(mappedBy = "autorizacoes", fetch = FetchType.LAZY) //muitas autorizacoes podem ter muitos usuarios
    @JsonIgnore
    private Set<Usuario> usuarios;
}
