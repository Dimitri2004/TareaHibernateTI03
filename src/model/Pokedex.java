package model;


import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "pokedex")
public class Pokedex {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nome", nullable = false, length = 100)
        private String nome;

        @Column(name = "peso")
        private BigDecimal peso;

        @Column(name = "misc")
        private String Misc;


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

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getMisc() {
        return Misc;
    }

    public void setMisc(String misc) {
        Misc = misc;
    }

    @Override
        public String toString() {
            return "Pokedex{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", peso='" + peso + '\''+
                    ", Misc='" + Misc + '\''+

                    '}';
        }
}
