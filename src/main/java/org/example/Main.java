package org.example;

import lombok.SneakyThrows;
import org.hibernate.Session;
import services.*;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {

//make migration for create and populate DB
        new FlywayMigration().flywayMigration();

//open hibernate session
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

//get client by id
        System.out.println("Client name is: " + new ClientCrudService().getById(4));
//create new client
        System.out.println("Created new client: " + new ClientCrudService().create("Maks"));
//change client name by id
        new ClientCrudService().setName(4, "Victor");
//delete client by id
        new ClientCrudService().deleteById(5);

//get planet by id
        System.out.println("Planet name is: " + new PlanetCrudService().getById("M16"));
//create new planet
        System.out.println("Created new planet: " + new PlanetCrudService().create("V12","Vavilon"));
//change planet name by id
        new PlanetCrudService().setName("V12","Victory");
//delete planet by id
        new PlanetCrudService().deleteById("V12");

//close hibernate session
        session.close();
//disconnect DB
        HibernateUtil.getInstance().close();
    }
}
