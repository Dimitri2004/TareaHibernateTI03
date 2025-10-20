package services;

import config.HibernateConfigP;

import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class PokemonService {

    public void crearPokemon(String nome, Date dataNacemento, Integer adestrador, Integer pokedexentry) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon novoPokemon = new Pokemon();
            novoPokemon.setNome(nome);
            novoPokemon.setDataNacemento(dataNacemento);
            novoPokemon.setAdestrador(adestrador);
            novoPokemon.setPokedexentry(pokedexentry);
            session.save(novoPokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crea-lo pokemon: " + e.getMessage());
        }
    }

    public Pokemon lerPokemon(Long id) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.get(Pokemon.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o gato: " + e.getMessage());
            return null;
        }
    }

    public void actualizarPokemon(Long id,String nome, Date dataNacemento, Integer adestrador, Integer pokedexentry) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                pokemon.setNome(nome);
                pokemon.setDataNacemento(dataNacemento);
                pokemon.setAdestrador(adestrador);
                pokemon.setPokedexentry(pokedexentry);
                session.update(pokemon);
            } else {
                System.out.println("Gato non encontrado para actualizar.");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo pokemon: " + e.getMessage());
        }
    }
    public void actualizarPokemon(Pokemon pokemon) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo pokemon: " + e.getMessage());
        }
    }

    public void eliminarPokemon(Long id) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                session.delete(pokemon);
            } else {
                System.out.println("non se atopou o gato");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Non quero eliminar ningun gatiño Y.Y: " + e.getMessage());
        }
    }


    public List<Pokemon> listarPokemon() {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.createQuery("from Pokemon", Pokemon.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao lista-los pokemon: " + e.getMessage());
            return null;
        }
    }
}
