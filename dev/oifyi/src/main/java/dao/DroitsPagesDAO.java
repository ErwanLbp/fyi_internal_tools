package dao;

import common.DroitsPages;
import common.MappingUrlFichier;
import common.Role;
import db.MyConnectorJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>dao DroitsPagesDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 24-10-2016
 */
public class DroitsPagesDAO {

    //TODO Javadoc : DroitsPagesDAO
    public static boolean isInDB(DroitsPages droitsPages) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM DROITS_PAGES WHERE id_role=? AND id_page=?")) {
            req.setInt(1, droitsPages.getId_role());
            req.setInt(2, droitsPages.getId_page());
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO Javadoc : DroitsPagesDAO
    public static List<MappingUrlFichier> getPages(int id_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<MappingUrlFichier> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT muf.id_muf, muf.nom_page, muf.nom_mode, muf.chemin_fichier FROM DROITS_PAGES dp, mapping_url_fichier muf WHERE dp.id_role=? AND dp.id_page=muf.id_muf")) {
            req.setInt(1, id_role);
            ResultSet res = req.executeQuery();
            while (res.next())
                list_res.add(new MappingUrlFichier(res.getInt("id_muf"), res.getString("nom_page"), res.getString("nom_mode"), res.getString("chemin_fichier")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_res;
    }

    //TODO Javadoc : DroitsPagesDAO
    public static List<Role> getRoles(int id_page) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<Role> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT r.id_role, r.libelle FROM DROITS_PAGES dp, ROLE r WHERE dp.id_page=? AND dp.ID_ROLE=r.ID_ROLE")) {
            req.setInt(1, id_page);
            ResultSet res = req.executeQuery();
            while (res.next())
                list_res.add(new Role(res.getInt("id_role"), res.getString("libelle")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_res;
    }

    //TODO Javadoc : DroitsPagesDAO
    public static boolean insert(DroitsPages droitsPages) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO DROITS_PAGES (id_page, id_role) VALUES(?,?)")) {
            req.setInt(1, droitsPages.getId_page());
            req.setInt(2, droitsPages.getId_role());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : DroitsPagesDAO
    public static boolean deleteOne(DroitsPages droitsPages) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM DROITS_PAGES WHERE id_page=? AND id_role=?")) {
            req.setInt(1, droitsPages.getId_page());
            req.setInt(2, droitsPages.getId_role());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : DroitsPagesDAO
    public static boolean isAllowed(int id_page, int id_role) {

        Role role_lambda = RoleDAO.get("lambda");

        List<Role> autorises = getRoles(id_page);
        for (Role r : autorises) {
            if (r.equals(role_lambda))
                return true;
            if (r.getId_role() == id_role)
                return true;
        }
        return false;
    }
}
