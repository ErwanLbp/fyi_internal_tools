package dao;

import common.MappingUrlFichier;
import common.MappingUrlFichierPK;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.List;

/**
 * <h1>dao MappingUrlFichierDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class MappingUrlFichierDAO {

    //TODO Javadoc : MappingUrlFichierDAO
    public static String getFichier(MappingUrlFichierPK mufpk) {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        MappingUrlFichier cheminFichier = session.get(MappingUrlFichier.class, mufpk);
        session.close();

        return (cheminFichier == null) ? null : cheminFichier.getCheminFichier();
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static List<MappingUrlFichier> getAll() {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        List<MappingUrlFichier> lmuf = (List<MappingUrlFichier>) session.createQuery("from MappingUrlFichier").list();
        session.close();

        return lmuf;
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static void insertOrUpdate(String page, String mode, String fichier) {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        MappingUrlFichier muf = new MappingUrlFichier(page, mode, fichier);
        session.saveOrUpdate(muf);
        session.getTransaction().commit();
        session.close();
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static void delete(MappingUrlFichierPK mufpk) {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        session.delete(new MappingUrlFichier(mufpk));
        session.getTransaction().commit();
        session.close();
    }
}
