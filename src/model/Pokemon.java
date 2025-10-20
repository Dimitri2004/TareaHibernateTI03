package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "nacemento")
    @Temporal(TemporalType.DATE)
    private Date dataNacemento;

    @Column(name = "pokedexentry")
    private Integer pokedexentry;

   @Column(name = "adestrador")
    private Integer adestrador;



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

    public Date getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(Date dataAdopcion) {
        this.dataNacemento = dataAdopcion;
    }

    public Integer getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(Integer adestrador) {
        this.adestrador = adestrador;
    }

    public Integer getPokedexentry() {
        return pokedexentry;
    }

    public void setPokedexentry(Integer pokedexentry) {
        this.pokedexentry = pokedexentry;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacemento='" + dataNacemento + '\''+
                ", entrada Pokedex='" + pokedexentry + '\''+
                ", entrenador Asignado='" + adestrador + '\''+
                '}';
    }



}
