package dao;

import common.CraJour;
import db.MyConnectorJDBC;

import java.sql.*;
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

    public static CraJour get(int id_cramois, Date jour) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_JOUR WHERE ID_CRAMOIS=? AND JOUR=?")) {
            req.setInt(1, id_cramois);
            req.setDate(2, jour);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new CraJour(res.getInt("id_cramois"), res.getDate("jour"), res.getDouble("heures_travail"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<CraJour> get(int id_cramois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_JOUR WHERE ID_CRAMOIS=?")) {
            req.setInt(1, id_cramois);
            ResultSet res = req.executeQuery();
            ArrayList<CraJour> listeCraJour = new ArrayList<CraJour>();
            while (res.next()){
                listeCraJour.add(new CraJour(res.getInt("id_cramois"), res.getDate("jour"), res.getDouble("heures_travail")));
            }
            return listeCraJour;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insert(CraJour cj) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO CRA_JOUR (ID_CRAMOIS, JOUR, HEURES_TRAVAIL) VALUES (?,?,?)")) {
            req.setInt(1, cj.getId_cramois());
            req.setDate(2, cj.getJour());
            req.setDouble(3, cj.getHeures_travail());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(CraJour cj) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE CRA_JOUR SET ID_CRAMOIS=?, JOUR=?, HEURES_TRAVAIL=? WHERE ID_CRAMOIS=? AND JOUR=?")) {
            req.setInt(1, cj.getId_cramois());
            req.setDate(2, cj.getJour());
            req.setDouble(3, cj.getHeures_travail());
            req.setInt(4, cj.getId_cramois());
            req.setDate(5, cj.getJour());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id_cramois, Date jour) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CRA_JOUR WHERE ID_CRAMOIS=? AND JOUR=?")) {
            req.setInt(1, id_cramois);
            req.setDate(2, jour);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id_cramois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CRA_JOUR WHERE ID_CRAMOIS=?")) {
            req.setInt(1, id_cramois);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
