package dao;

import common.Consultant;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <h1>dao ConsultantDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class ConsultantDAO {

    public static boolean checkLoginPassword(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Consultant> listConsultants = (List<Consultant>) session.createQuery("from Consultant WHERE username=" + login + " AND password = " + password);
        Query queryObject = session.createQuery("from Consultant WHERE username=? AND password = ?");
        queryObject.setParameter(0,login);
        queryObject.setParameter(1,password);
        session.close();
        try {
            Consultant consultant = (Consultant) queryObject.getSingleResult();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
