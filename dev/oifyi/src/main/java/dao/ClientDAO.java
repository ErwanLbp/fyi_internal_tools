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
                return new Client(res.getInt("id_client"), res.getString("raison_sociale"), res.getString("forme_juridique"), res.getString("siret"), res.getString("num_tva"), res.getString("rcs"), res.getInt("adresse_numero"), res.getString("adresse_rue"), res.getInt("adresse_cp"), res.getString("adresse_ville"), String.valueOf(res.getInt("telephone")), res.getInt("capital"), res.getString("ville_inscription"), res.getString("representant_nom"), res.getString("representant_fonction"), String.valueOf(res.getInt("respo_client_tel")), res.getString("contact_achats_nom"), String.valueOf(res.getInt("contact_achats_tel")), res.getString("respo_fournisseur_nom"));
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
                listeClients.add(new Client(res.getInt("id_client"), res.getString("raison_sociale"), res.getString("forme_juridique"), res.getString("siret"), res.getString("num_tva"), res.getString("rcs"), res.getInt("adresse_numero"), res.getString("adresse_rue"), res.getInt("adresse_cp"), res.getString("adresse_ville"), String.valueOf(res.getInt("telephone")), res.getInt("capital"), res.getString("ville_inscription"), res.getString("representant_nom"), res.getString("representant_fonction"), String.valueOf(res.getInt("respo_client_tel")), res.getString("contact_achats_nom"), String.valueOf(res.getInt("contact_achats_tel")), res.getString("respo_fournisseur_nom")));
            return listeClients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeClients;
    }


    //TODO Javadoc : ClientDAO
    public static Client getByFormeJuridique(String forme_juridique) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CLIENT WHERE FORME_JURIDIQUE=?")) {
            req.setString(1, forme_juridique);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Client(res.getInt("id_client"), res.getString("raison_sociale"), res.getString("forme_juridique"), res.getString("siret"), res.getString("num_tva"), res.getString("rcs"), res.getInt("adresse_numero"), res.getString("adresse_rue"), res.getInt("adresse_cp"), res.getString("adresse_ville"), String.valueOf(res.getInt("telephone")), res.getInt("capital"), res.getString("ville_inscription"), res.getString("representant_nom"), res.getString("representant_fonction"), String.valueOf(res.getInt("respo_client_tel")), res.getString("contact_achats_nom"), String.valueOf(res.getInt("contact_achats_tel")), res.getString("respo_fournisseur_nom"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ClientDAO
    public static boolean insert(Client client) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO client(RAISON_SOCIALE, RAISON_SOCIALE, FORME_JURIDIQUE, SIRET, NUM_TVA, RCS, ADRESSE_NUMERO, ADRESSE_RUE, ADRESSE_CP, ADRESSE_VILLE, TELEPHONE, CAPITAL, VILLE_INSCRIPTION, REPRESENTANT_NOM, REPRESENTANT_FONCTION, RESPO_CLIENT_TEL, CONTACT_ACHATS_NOM, CONTACT_ACHATS_TEL, RESPO_FOURNISSEUR_NOM, RESPO_FOURNISSEUR_FONCTION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            req.setInt(1, client.getId());
            req.setString(2, client.getRaison_sociale());
            req.setString(3, client.getForme_juridique());
            req.setString(4, client.getSiret());
            req.setString(5, client.getNum_tva());
            req.setString(6, client.getRcs());
            req.setInt(7, client.getAdresse_numero());
            req.setString(8, client.getAdresse_rue());
            req.setInt(9, client.getAdresse_cp());
            req.setString(10, client.getAdresse_ville());
            req.setString(11, client.getTelephone());
            req.setInt(12, client.getCapital());
            req.setString(13, client.getVille_inscription());
            req.setString(14, client.getRepresentant_nom());
            req.setString(15, client.getRepresentant_fonction());
            req.setString(16, client.getRespo_client_tel());
            req.setString(17, client.getContact_achats_nom());
            req.setString(18, client.getContact_achats_tel());
            req.setString(19, client.getRespo_fournisseur_nom());
            req.setString(20, client.getRespo_fournisseur_fonction());
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

        try (PreparedStatement req = connection.prepareStatement("UPDATE client SET RAISON_SOCIALE=?, RAISON_SOCIALE=?, FORME_JURIDIQUE=?, SIRET=?, NUM_TVA=?, RCS=?, ADRESSE_NUMERO=?, ADRESSE_RUE=?, ADRESSE_CP=?, ADRESSE_VILLE=?, TELEPHONE=?, CAPITAL=?, VILLE_INSCRIPTION=?, REPRESENTANT_NOM=?, REPRESENTANT_FONCTION=?, RESPO_CLIENT_TEL=?, CONTACT_ACHATS_NOM=?, CONTACT_ACHATS_TEL=?, RESPO_FOURNISSEUR_NOM =? WHERE ID_CLIENT=?")) {
            req.setString(1, client.getRaison_sociale());
            req.setString(2, client.getForme_juridique());
            req.setString(3, client.getSiret());
            req.setString(4, client.getNum_tva());
            req.setString(5, client.getRcs());
            req.setInt(6, client.getAdresse_numero());
            req.setString(7, client.getAdresse_rue());
            req.setInt(8, client.getAdresse_cp());
            req.setString(9, client.getAdresse_ville());
            req.setString(10, client.getTelephone());
            req.setInt(11, client.getCapital());
            req.setString(12, client.getVille_inscription());
            req.setString(13, client.getRepresentant_nom());
            req.setString(14, client.getRepresentant_fonction());
            req.setString(15, client.getRespo_client_tel());
            req.setString(16, client.getContact_achats_nom());
            req.setString(17, client.getContact_achats_tel());
            req.setString(18, client.getRespo_fournisseur_nom());
            req.setInt(19, client.getId());
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

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM client WHERE ID_CLIENT=?")) {
            req.setInt(1, id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ClientDAO
    public static boolean deleteByFormeJuridique(String forme_juridique) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM client WHERE FORME_JURIDIQUE=?")) {
            req.setString(1, forme_juridique);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
