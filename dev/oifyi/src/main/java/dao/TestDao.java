package dao;

import common.Consultant;
import org.hibernate.Session;
import persistence.HibernateUtil;

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
