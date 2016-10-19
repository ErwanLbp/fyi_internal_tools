package common;

import org.hibernate.Session;
import persistence.CustomFlyway;
import persistence.HibernateUtil;

public class App {
    public static void main(String[] args) {

        // Lancement des migrations Flyway
        CustomFlyway.migration();

        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Consultant consultant = new Consultant();

        consultant.setNom("Alexis");
        consultant.setPrenom("Doan");

        Consultant yasmine = new Consultant();
        yasmine.setPrenom("Yasmine");
        yasmine.setNom("Guedjou");

        session.save(consultant);
        session.saveOrUpdate(yasmine);

        consultant.setNom("Autre");
        session.saveOrUpdate(consultant);
        session.getTransaction().commit();

        session.close();

        System.out.println("***** FIN *****");
    }
}