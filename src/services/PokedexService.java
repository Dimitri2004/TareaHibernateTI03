package services;
import config.HibernateConfigP;
import model.Pokedex;
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
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class, id);
            if (pokedex != null) {
                session.delete(pokedex);
            } else {
                System.out.println("non se atopou o gato");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Non quero eliminar ningun gatiño Y.Y: " + e.getMessage());
        }
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
