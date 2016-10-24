package dao;

import common.MappingUrlFichier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MyConnectorJDBC;

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
    public static MappingUrlFichier getMuf(String nom_page, String nom_mode) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return null;

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM mapping_url_fichier WHERE nom_page=? AND nom_mode=?")) {
            req.setString(1, nom_page);
            req.setString(2, nom_mode);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new MappingUrlFichier(res.getInt("id_muf"), res.getString("nom_page"), res.getString("nom_mode"), res.getString("chemin_fichier"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static List<MappingUrlFichier> getAll() {

        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return null;

        List<MappingUrlFichier> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM mapping_url_fichier")) {
            ResultSet res = req.executeQuery();
            while (res.next()) {
                list_res.add(new MappingUrlFichier(res.getInt("id_muf"), res.getString("nom_page"), res.getString("nom_mode"), res.getString("chemin_fichier")));
            }
            return list_res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static boolean insert(MappingUrlFichier muf) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return false;

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO mapping_url_fichier (nom_page, nom_mode, chemin_fichier) VALUES(?,?,?)")) {
            req.setString(1, muf.getNomPage());
            req.setString(2, muf.getNomMode());
            req.setString(3, muf.getCheminFichier());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static boolean update(MappingUrlFichier muf) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return false;

        try (PreparedStatement req = connection.prepareStatement("UPDATE mapping_url_fichier SET nom_page=?, nom_mode=?, chemin_fichier=? WHERE id_muf=?")) {
            req.setString(1, muf.getNomPage());
            req.setString(2, muf.getNomMode());
            req.setString(3, muf.getCheminFichier());
            req.setInt(4, muf.getId_muf());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MappingUrlFichierDAO
    public static boolean deleteByNomPageNomMode(String nom_page, String nom_mode) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return false;

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM mapping_url_fichier WHERE nom_page=? AND nom_mode=?")) {
            req.setString(1, nom_page);
            req.setString(2, nom_mode);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
