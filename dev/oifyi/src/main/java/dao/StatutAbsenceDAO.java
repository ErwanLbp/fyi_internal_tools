package dao;

import common.StatutAbsence;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>dao StatutAbsenceDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 24-10-2016
 */
public class StatutAbsenceDAO {

    //TODO Javadoc : StatutAbsenceDAO
    public static StatutAbsence get(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM statut_absence WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new StatutAbsence(res.getInt("id_statut_absence"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : StatutAbsenceDAO
    public static StatutAbsence get(int id_statut_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM statut_absence WHERE id_statut_absence=?")) {
            req.setInt(1, id_statut_absence);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new StatutAbsence(res.getInt("id_statut_absence"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : StatutAbsenceDAO
    public static List<StatutAbsence> getAll() {

        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<StatutAbsence> list_res = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM statut_absence ORDER BY ID_statut_absence");
            while (res.next())
                list_res.add(new StatutAbsence(res.getInt("id_statut_absence"), res.getString("libelle")));
        } catch (SQLException e) {
            e.printStackTrace();
            list_res.clear();
        }
        return list_res;
    }

    //TODO Javadoc : StatutAbsenceDAO
    public static boolean insert(StatutAbsence statut_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO statut_absence (id_statut_absence, libelle) VALUES(?,?)")) {
            req.setInt(1, statut_absence.getId_statut_absence());
            req.setString(2, statut_absence.getLibelle());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : StatutAbsenceDAO
    public static boolean update(StatutAbsence statut_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE statut_absence SET LIBELLE=? WHERE id_statut_absence=?")) {
            req.setString(1, statut_absence.getLibelle());
            req.setInt(2, statut_absence.getId_statut_absence());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : StatutAbsenceDAO
    public static boolean delete(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM statut_absence WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : StatutAbsenceDAO
    public static boolean isInDB(int statut_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM statut_absence WHERE ID_statut_absence=?")) {
            req.setInt(1, statut_absence);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
