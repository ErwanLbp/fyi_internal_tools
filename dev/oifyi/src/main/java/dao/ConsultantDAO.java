package dao;

import common.Consultant;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>dao ConsultantDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class ConsultantDAO {

    //TODO Javadoc : ConsultantDAO
    public static boolean checkLoginPassword(String login, String password) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT username, password FROM CONSULTANT WHERE USERNAME=? AND password=?")) {
            req.setString(1, login);
            req.setString(2, password);
            ResultSet res = req.executeQuery();
            if (res.next() && !res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO Javadoc : ConsultantDAO
    public static Consultant getById(int i) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE ID_CONSULTANT=?")) {
            req.setInt(1, i);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"), res.getInt("role_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ConsultantDAO
    public static ArrayList<Consultant> getAll() throws SQLException {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Consultant> listeConsultants = new ArrayList<Consultant>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM CONSULTANT");
            while (res.next())
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"), res.getInt("role_id")));
            return listeConsultants;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }

    //TODO Javadoc : ConsultantDAO
    public static boolean isAdmin(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT c.username, r.libelle FROM CONSULTANT c, ROLE r WHERE c.ID_CONSULTANT=? AND lower(r.LIBELLE)='admin' AND c.ROLE_ID=r.ID_ROLE")) {
            req.setInt(1, id_consultant);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO Javadoc : ConsultantDAO
    public static Consultant getByLogin(String login) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE USERNAME=?")) {
            req.setString(1, login);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"), res.getInt("role_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
