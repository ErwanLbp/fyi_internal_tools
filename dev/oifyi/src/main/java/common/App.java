package main.java.common;


import main.java.persistence.HibernateUtil;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Consultant consultant = new Consultant();

        consultant.setNom("Alexis");
        consultant.setPrenom("Doan");

        session.saveOrUpdate(consultant);
        session.getTransaction().commit();
    }
}