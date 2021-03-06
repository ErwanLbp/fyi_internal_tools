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
    public static Consultant get(int i) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE ID_CONSULTANT=?")) {
            req.setInt(1, i);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Consultant(res.getInt("ID_CONSULTANT"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ConsultantDAO
    public static ArrayList<Consultant> getAll(String orderBy) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        String reqOrderBy = orderBy == null ? "id_consultant" : orderBy;

        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM CONSULTANT ORDER BY " + reqOrderBy + " DESC");
            while (res.next())
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password")));
        } catch (SQLException e) {
            e.printStackTrace();
            listeConsultants.clear();
        }
        return listeConsultants;
    }

    //TODO Javadoc : ConsultantDAO
    public static boolean isAdmin(int id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT co.ID_CONSULTANT, co.NOM, co.prenom, co.USERNAME, co.PASSWORD FROM CONSULTANT co, ROLE ro, CONSULTANT_ROLE cr WHERE lower(ro.libelle)='admin' AND co.ID_CONSULTANT=? AND co.ID_CONSULTANT=cr.ID_CONSULTANT AND cr.ID_ROLE = ro.ID_ROLE")) {
            req.setInt(1, id);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO Javadoc : ConsultantDAO
    public static Consultant get(String login) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE USERNAME=?")) {
            req.setString(1, login);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Consultant(res.getInt("ID_CONSULTANT"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ConsultantDAO
    public static boolean insert(Consultant consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO consultant (ID_CONSULTANT, NOM, PRENOM, USERNAME, PASSWORD) VALUES (?,?,?,?,?)")) {
            req.setInt(1, consultant.getId());
            req.setString(2, consultant.getNom());
            req.setString(3, consultant.getPrenom());
            req.setString(4, consultant.getUsername());
            req.setString(5, consultant.getPassword());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Consultant consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE consultant SET nom = ?,prenom = ?, username = ?, password = ? WHERE ID_CONSULTANT=?")) {
            req.setString(1, consultant.getNom());
            req.setString(2, consultant.getPrenom());
            req.setString(3, consultant.getUsername());
            req.setString(4, consultant.getPassword());
            req.setInt(5, consultant.getId());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ConsultantDAO
    public static boolean delete(int id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM consultant WHERE ID_CONSULTANT=?")) {
            req.setInt(1, id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ConsultantDAO
    public static boolean delete(String username) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM consultant WHERE USERNAME=?")) {
            req.setString(1, username);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isInDB(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE ID_CONSULTANT=?")) {
            req.setInt(1, id_consultant);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
