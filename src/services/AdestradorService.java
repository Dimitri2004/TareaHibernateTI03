package services;

import config.HibernateConfigP;
import model.Adestrador;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class AdestradorService {
    public void crearAdestrador(String nome, Date nacemento) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador novoAdestrador = new Adestrador();
            novoAdestrador.setNome(nome);
            novoAdestrador.setDataNacemento(nacemento);
            session.save(novoAdestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crea-lo adestrador: " + e.getMessage());
        }
    }

    public Adestrador lerAdestrador(Long id) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.get(Adestrador.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o gato: " + e.getMessage());
            return null;
        }
    }

    public void actualizarAdestrador(Long id,String nome, Date dataNacemento) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = session.get(Adestrador.class, id);
            if (adestrador != null) {
                adestrador.setNome(nome);
                adestrador.setDataNacemento(dataNacemento);
                session.update(adestrador);
            } else {
                System.out.println("Adestrador non encontrado para actualizar.");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo pokemon: " + e.getMessage());
        }
    }
    public void actualizarAdestrador(Adestrador adestrador) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualiza-lo pokemon: " + e.getMessage());
        }
    }

    public void eliminarAdestrador(Long id) {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador adestrador = session.get(Adestrador.class, id);
            if (adestrador != null) {
                session.delete(adestrador);
            } else {
                System.out.println("non se atopou o gato");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Non quero eliminar ningun gatiño Y.Y: " + e.getMessage());
        }
    }


    public List<Adestrador> listAdestrador() {
        try (Session session = HibernateConfigP.getSessionFactory().openSession()) {
            return session.createQuery("from Adestrador", Adestrador.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao lista-los pokemon: " + e.getMessage());
            return null;
        }
    }
}
