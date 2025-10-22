
import model.Adestrador;
import model.Pokedex;
import model.Pokemon;

import services.AdestradorService;
import services.PokedexService;
import services.PokemonService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AdestradorService adestradorService=new AdestradorService();
        PokemonService pokemonService=new PokemonService();
        PokedexService pokedexService=new PokedexService();

        List<Pokemon> pokemonActualizar= new ArrayList<>();
        List<Adestrador> adestradorActualizar= new ArrayList<>();
        List<Pokedex> pokedexActualizar=new ArrayList<>();


        adestradorService.crearAdestrador("marcos",new Date(24-5-1056));
        adestradorService.crearAdestrador("lucas",new Date(25-6-1045));


        System.out.println("Listado de todos os pokemon:");
        List<Pokemon> pokemons = pokemonService.listarPokemon();
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
        }
        System.out.println("Listado de todos os Adestradores:");
        List<Adestrador> adestradors = adestradorService.listAdestrador();
        for (Adestrador adestrador : adestradors) {
            System.out.println(adestrador);
        }
        System.out.println("Listado de toda a Pokedex:");
        List<Pokedex> pokedexes = pokedexService.pokedexList();
        for (Pokedex pokedex : pokedexes) {
            System.out.println(pokedex);
        }

        pokemonActualizar.add(pokemonService.lerPokemonPorIdDono( 10,"caterpie"));

        for (Pokemon pokemon:pokemonActualizar){
            System.out.println("Ancianificamos a "+pokemon.getNome());
            pokemon.setAdestrador(11);
            pokemonService.actualizarPokemon(pokemon);
            System.out.println("Pokemon Actualizado");
        }
        System.out.println("Listado de todos os pokemon actualizados:");
        List<Pokemon> pokemons2 = pokemonService.listarPokemon();
        for (Pokemon pokemon : pokemons2) {
            System.out.println(pokemon);
        }

        adestradorActualizar.add(adestradorService.lerAdestradorNome("Almonefo"));

        for (Adestrador adestrador:adestradorActualizar){
            System.out.println("Ancianificamos a "+adestrador.getNome());
            adestrador.setNome("Almonefo");
            System.out.println("Ancianificamos a "+adestrador.getNome());
            adestradorService.actualizarAdestrador(adestrador);
            System.out.println("Pokemon Actualizado");
        }
        System.out.println("Listado de todos os adestradores actualizados:");
        List<Adestrador> adestradors1= adestradorService.listAdestrador();
        for (Adestrador adestrador : adestradors1) {
            System.out.println(adestrador);
        }
        pokedexActualizar.add(pokedexService.lerPokedexNomePokemon("caterpie"));
        for (Pokedex pokedex:pokedexActualizar){
            System.out.println("Ancianificamos a "+pokedex.getNome());
            pokedex.setNome("Nunelby");
            System.out.println("Ancianificamos a "+pokedex.getNome());
            pokedexService.actualizarPokedex(pokedex);
            System.out.println("Pokemon Actualizado");
        }
        System.out.println("Listado de todos os pokemon actualizados de pokedex:");
        List<Pokedex> p= pokedexService.pokedexList();
        for (Pokedex pokedex : p) {
            System.out.println(pokedex);
        }



    }


}

