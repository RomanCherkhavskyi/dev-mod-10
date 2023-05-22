package services;

import org.example.entity.Planet;
import org.hibernate.Session;

public class PlanetCrudService {
    public String create(String planet_id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        String planeta = "-1";
        try {
            session.beginTransaction();
            Planet planet = new Planet();
            planet.setId(planet_id);
            planet.setName(name);
            session.persist(planet);

            session.getTransaction().commit();

            Planet planetRead = session.createQuery("FROM Planet p WHERE p.name = :name", Planet.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (planetRead != null) {
                planeta = planetRead.getId() + ", " + planetRead.getName();
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return planeta;
    }

    public String getById(String id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet.getName();
    }

    public void setName(String id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            session.persist(planet);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteById(String id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            session.remove(planet);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
