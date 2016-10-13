package main.java.dao;

import main.java.common.Consultant;
import main.java.persistence.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TestDao {
    public static List<Consultant> findAllConsultant() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Consultant> list_consultant = session.createQuery("from Consultant").list();
        session.close();
        return list_consultant;
    }
}
