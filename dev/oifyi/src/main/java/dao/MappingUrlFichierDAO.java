package dao;

import common.MappingUrlFichier;
import common.MappingUrlFichierPK;
import org.hibernate.Session;
import persistence.HibernateUtil;

/**
 * <h1>dao MappingUrlFichierDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class MappingUrlFichierDAO {
    public static String getFichier(MappingUrlFichierPK mufpk) {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        MappingUrlFichier cheminFichier = session.get(MappingUrlFichier.class, mufpk);
        session.close();

        return (cheminFichier == null) ? null : cheminFichier.getCheminFichier();
    }

    public static void insertOrUpdate(String page, String mode, String fichier) {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        MappingUrlFichier muf = new MappingUrlFichier(page, mode, fichier);
        session.saveOrUpdate(muf);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(MappingUrlFichierPK mufpk) {
        Session session = HibernateUtil.getSessionFactory();
        session.beginTransaction();

        session.delete(new MappingUrlFichier(mufpk));
        session.getTransaction().commit();
        session.close();
    }
}
