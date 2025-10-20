package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="adestrador")
public class Adestrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nacemento")
    @Temporal(TemporalType.DATE)
    private Date nacemento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNacemento(Date d) {
        this.nacemento = d;
    }

    public Date getDataNacemento() {
        return nacemento;
    }



    @Override
    public String toString() {
        return "Adestrador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacimiento= '"+ nacemento +"' ";

    }
}
