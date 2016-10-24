package dao;

import common.Client;
import common.Consultant;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>dao ConsultantDAO</h1>
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 25-10-2016
 */
public class ClientDAO {

    //TODO Javadoc : ClientDAO
    public static Client getById(int i) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CLIENT WHERE ID_CLIENT=?")) {
            req.setInt(1, i);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ClientDAO
    public static ArrayList<Client> getAll() throws SQLException {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Client> listeClients = new ArrayList<Client>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM CLIENT");
            while (res.next())
                listeClients.add(new Client(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom")));
            return listeClients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeClients;
    }


    //TODO Javadoc : ClientDAO
    public static Client getByNomPrenom(String nom, String prenom) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CLIENT WHERE NOM=? AND PRENOM=?")) {
            req.setString(1, nom);
            req.setString(2,prenom);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Client(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ClientDAO
    public static boolean create(Client client) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO client (ID_CONSULTANT, NOM, PRENOM) VALUES (?,?,?)")) {
            req.setInt(1, client.getId());
            req.setString(2, client.getNom());
            req.setString(3, client.getPrenom());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ClientDAO
    public static boolean update(Client client) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE client SET nom = ?,prenom = ? WHERE ID_CLIENT=?")) {
            req.setString(1, client.getNom());
            req.setString(2, client.getPrenom());
            req.setInt(3, client.getId());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ClientDAO
    public static boolean deleteById(int id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM client WHERE id_client=?")) {
            req.setInt(1, id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
