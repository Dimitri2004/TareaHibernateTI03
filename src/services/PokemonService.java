package services;

import config.HibernateConfigP;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class PokemonService {

    /**
     * Crea un nuevo Pokémon en la base de datos.
     */
    public void crearPokemon(String nome, Date dataNacemento, Integer adestrador, Integer pokedexentry) {
        Transaction transaction = null;
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokemon novoPokemon = new Pokemon();
            novoPokemon.setNome(nome);
            novoPokemon.setDataNacemento(dataNacemento);
            novoPokemon.setAdestrador(adestrador);
            novoPokemon.setPokedexentry(pokedexentry);

            session.persist(novoPokemon);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Erro ao crear o Pokémon: " + e.getMessage());
        }
    }

    /**
     * Lee un Pokémon por su ID.
     */
    public Pokemon lerPokemon(Long id) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.get(Pokemon.class, id);
        } catch (Exception e) {
            System.err.println("❌ Erro ao ler o Pokémon: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza un Pokémon por ID con nuevos datos.
     */
    public void actualizarPokemon(Long id, String nome, Date dataNacemento, Integer adestrador, Integer pokedexentry) {
        Transaction transaction = null;
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon == null) {
                System.out.println("Pokémon non atopado para actualizar.");
                return;
            }

            pokemon.setNome(nome);
            pokemon.setDataNacemento(dataNacemento);
            pokemon.setAdestrador(adestrador);
            pokemon.setPokedexentry(pokedexentry);

            session.merge(pokemon);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Erro ao actualizar o Pokémon: " + e.getMessage());
        }
    }

    /**
     * Actualiza un Pokémon ya existente.
     */
    public void actualizarPokemon(Pokemon pokemon) {
        if (pokemon == null) {
            System.err.println("Non se pode actualizar un Pokémon nulo.");
            return;
        }

        Transaction transaction = null;
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pokemon);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Erro ao actualizar o Pokémon: " + e.getMessage());
        }
    }

    /**
     * Elimina un Pokémon por su ID.
     */
    public void eliminarPokemon(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon == null) {
                System.out.println(" Non se atopou o Pokémon para eliminar.");
                return;
            }

            session.remove(pokemon);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Erro ao eliminar o Pokémon: " + e.getMessage());
        }
    }

    /**
     * Lista todos los Pokémon almacenados.
     */
    public List<Pokemon> listarPokemon() {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.createQuery("from Pokemon", Pokemon.class).list();
        } catch (Exception e) {
            System.err.println("Erro ao listar os Pokémon: " + e.getMessage());
            return List.of(); // Retorna lista vacía en vez de null
        }
    }

    /**
     * Busca un Pokémon por su nombre y adestrador.
     */
    public Pokemon lerPokemonPorIdDono(int adestrador, String nome) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            List<Pokemon> resultados = session.createQuery(
                            "from Pokemon where adestrador = :adestrador and nome = :nome", Pokemon.class)
                    .setParameter("adestrador", adestrador)
                    .setParameter("nome", nome)
                    .list();

            if (resultados.isEmpty()) {
                System.out.printf("Non se atopou o Pokémon '%s' co adestrador %d.%n", nome, adestrador);
                return null;
            }

            return resultados.get(0); // Supón que nome + adestrador son únicos
        } catch (Exception e) {
            System.err.println("Erro ao ler o Pokémon: " + e.getMessage());
            return null;
        }
    }
}

