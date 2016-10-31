package dao;

import common.CraJour;
import db.MyConnectorJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1>dao CraJourDAO</h1>
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 25-10-2016
 */
public class CraJourDAO {

    public static CraJour get(int id_cramois, int jour) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_JOUR WHERE ID_CRAMOIS=? AND JOUR=?")) {
            req.setInt(1, id_cramois);
            req.setInt(2, jour);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new CraJour(res.getInt("id_cramois"), res.getInt("jour"), res.getDouble("travail"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<CraJour> get(int id_cramois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<CraJour> listeCraJour = new ArrayList<CraJour>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_JOUR WHERE ID_CRAMOIS=?")) {
            req.setInt(1, id_cramois);
            ResultSet res = req.executeQuery();
            while (res.next())
                listeCraJour.add(new CraJour(res.getInt("id_cramois"), res.getInt("jour"), res.getDouble("travail")));
            return listeCraJour;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCraJour;
    }

    public static boolean insert(CraJour cj) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO CRA_JOUR (ID_CRAMOIS, JOUR, TRAVAIL) VALUES (?,?,?)")) {
            req.setInt(1, cj.getId_cramois());
            req.setInt(2, cj.getJour());
            req.setDouble(3, cj.getTravail());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(CraJour cj) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE CRA_JOUR SET ID_CRAMOIS=?, JOUR=?, TRAVAIL=? WHERE ID_CRAMOIS=? AND JOUR=?")) {
            req.setInt(1, cj.getId_cramois());
            req.setInt(2, cj.getJour());
            req.setDouble(3, cj.getTravail());
            req.setInt(4, cj.getId_cramois());
            req.setInt(5, cj.getJour());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id_cramois, int jour) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CRA_JOUR WHERE ID_CRAMOIS=? AND JOUR=?")) {
            req.setInt(1, id_cramois);
            req.setInt(2, jour);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int delete(int id_cramois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CRA_JOUR WHERE ID_CRAMOIS=?")) {
            req.setInt(1, id_cramois);
            return req.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getInsert() {
        return "INSERT INTO CRA_JOUR (ID_CRAMOIS, JOUR, TRAVAIL) VALUES (?,?,?)";
    }

}
