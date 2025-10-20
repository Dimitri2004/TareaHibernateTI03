
import model.Adestrador;
import model.Pokedex;
import model.Pokemon;

import services.AdestradorService;
import services.PokedexService;
import services.PokemonService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AdestradorService adestradorService=new AdestradorService();
        PokemonService pokemonService=new PokemonService();
        PokedexService pokedexService=new PokedexService();


        /**adestradorService.crearAdestrador("marcos",new Date(24-5-1056));
        adestradorService.crearAdestrador("lucas",new Date(25-6-1045));**/


        System.out.println("Listado de todos os gatos:");
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

    }


}

