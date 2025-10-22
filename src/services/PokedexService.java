package services;
import config.HibernateConfigP;
import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.List;

public class PokedexService {
    public void crearPokedex(String nome, BigDecimal peso, String misc) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokedex novaPokedex = new Pokedex();
            novaPokedex.setNome(nome);
            novaPokedex.setPeso(peso);
            novaPokedex.setMisc(misc);
            session.save(novaPokedex);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crea-lo adestrador: " + e.getMessage());
        }
    }
    public void actualizarPokedex(Long id,String nome, BigDecimal peso,String misc) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class, id);
            if (pokedex != null) {
                pokedex.setNome(nome);
                pokedex.setPeso(peso);
                pokedex.setMisc(misc);
                session.update(peso);
            } else {
                System.out.println("Adestrador non encontrado para actualizar.");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo pokemon: " + e.getMessage());
        }
    }
    public void actualizarPokedex(Pokedex pokedex) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pokedex);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo pokemon: " + e.getMessage());
        }
    }
    public void eliminarPokedex(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConfigP.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Pokedex p = session.find(Pokedex.class, id);
            if (p != null) {
                session.remove(p);
            } else {
                System.out.println("No se encontró el pokedex con ID: " + id);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (IllegalStateException ignored) {}
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Pokedex lerPokedexNomePokemon(String nome) {
        Pokedex pokedex=null;
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            List<Pokedex> pokedexList = session.createQuery("from Pokedex where nome = :nome", Pokedex.class)
                    .setParameter("nome", nome)
                    .getResultList();
            if (!pokedexList.isEmpty()) {
                pokedex = pokedexList.get(0);  // Suponemos que el nombre y el dueño son únicos
            } else {
                System.out.println("Non se atopou o pokemon co nome " + nome);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o adestrador: " + e.getMessage());
            return null;
        }
        return pokedex;
    }
    public List<Pokedex> pokedexList() {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.createQuery("from Pokedex", Pokedex.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao lista-los pokemon: " + e.getMessage());
            return null;
        }
    }
}
