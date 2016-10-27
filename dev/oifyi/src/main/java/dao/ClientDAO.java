package dao;

import common.Adresse;
import common.Client;
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
    public static Client get(int id_client) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CLIENT WHERE ID_CLIENT=?")) {
            req.setInt(1, id_client);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Client(res.getInt("id_client"), res.getString("raison_sociale"), res.getString("forme_juridique"), res.getString("siret"), res.getString("num_tva"), new Adresse(res.getInt("adresse_numero"), res.getString("adresse_rue"), res.getInt("adresse_cp"), res.getString("adresse_ville")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ClientDAO
    public static ArrayList<Client> getAll() {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Client> listeClients = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM CLIENT");
            while (res.next())
                listeClients.add(new Client(res.getInt("id_client"), res.getString("raison_sociale"), res.getString("forme_juridique"), res.getString("siret"), res.getString("num_tva"), new Adresse(res.getInt("adresse_numero"), res.getString("adresse_rue"), res.getInt("adresse_cp"), res.getString("adresse_ville"))));
            return listeClients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeClients;
    }

    //TODO Javadoc : ClientDAO
    public static Client get(String raison_sociale) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CLIENT WHERE lower(RAISON_SOCIALE)=lower(?)")) {
            req.setString(1, raison_sociale);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Client(res.getInt("id_client"), res.getString("raison_sociale"), res.getString("forme_juridique"), res.getString("siret"), res.getString("num_tva"), new Adresse(res.getInt("adresse_numero"), res.getString("adresse_rue"), res.getInt("adresse_cp"), res.getString("adresse_ville")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ClientDAO
    public static boolean insert(Client client) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO client(RAISON_SOCIALE, FORME_JURIDIQUE, SIRET, NUM_TVA, ADRESSE_NUMERO, ADRESSE_RUE, ADRESSE_CP, ADRESSE_VILLE) VALUES (?,?,?,?,?,?,?,?)")) {
            req.setString(1, client.getRaison_sociale());
            req.setString(2, client.getForme_juridique());
            req.setString(3, client.getSiret());
            req.setString(4, client.getNum_tva());
            req.setInt(5, client.getAdresse().getNumero());
            req.setString(6, client.getAdresse().getRue());
            req.setInt(7, client.getAdresse().getCp());
            req.setString(8, client.getAdresse().getVille());
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

        try (PreparedStatement req = connection.prepareStatement("UPDATE CLIENT SET RAISON_SOCIALE=?, FORME_JURIDIQUE=?, SIRET=?, NUM_TVA=?, ADRESSE_NUMERO=?, ADRESSE_RUE=?, ADRESSE_CP=?, ADRESSE_VILLE=? WHERE ID_CLIENT=?")) {
            req.setString(1, client.getRaison_sociale());
            req.setString(2, client.getForme_juridique());
            req.setString(3, client.getSiret());
            req.setString(4, client.getNum_tva());
            req.setInt(5, client.getAdresse().getNumero());
            req.setString(6, client.getAdresse().getRue());
            req.setInt(7, client.getAdresse().getCp());
            req.setString(8, client.getAdresse().getVille());
            req.setInt(9, client.getId());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ClientDAO
    public static boolean delete(int id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM client WHERE ID_CLIENT=?")) {
            req.setInt(1, id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ClientDAO
    public static boolean delete(String raison_sociale) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM client WHERE RAISON_SOCIALE=?")) {
            req.setString(1, raison_sociale);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
