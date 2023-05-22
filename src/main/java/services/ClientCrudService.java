package services;

import lombok.SneakyThrows;
import org.example.entity.Client;
import org.hibernate.Session;

public class ClientCrudService {

    @SneakyThrows
    public int create(String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        int id = -1;
        try {
            session.beginTransaction();
            Client client = new Client();
            client.setName(name);
            session.persist(client);
            session.getTransaction().commit();

            Client clientRead = session.createQuery("FROM Client c WHERE c.name = :name", Client.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (clientRead != null) {
                id = clientRead.getId();
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    public String getById(int id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client.getName();
    }

    public void setName(int id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setName(name);
            session.persist(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteById(int id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.remove(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
