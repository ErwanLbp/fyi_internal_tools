package dao;

import common.CraMois;
import db.MyConnectorJDBC;

import java.sql.*;

/**
 * <h1>dao CraMoisDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class CraMoisDAO {

    public static CraMois get(int id_cra_mois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, id_cra_mois);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CraMois get(int mission_id, int consultant_id, String moisAnnee) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE MISSION_ID=? AND CONSULTANT_ID=? AND MOIS_ANNEE=?")) {
            req.setInt(1, mission_id);
            req.setInt(2, consultant_id);
            req.setDate(3, Date.valueOf(moisAnnee));
            ResultSet res = req.executeQuery();
            if (res.next())
                return new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insert(CraMois cm) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO CRA_MOIS (MISSION_ID, CONSULTANT_ID, MOIS_ANNEE, STATUS_ID) VALUES (?,?,?,?)")) {
            req.setInt(1, cm.getMission_id());
            req.setInt(2, cm.getConsultant_id());
            req.setDate(3, cm.getMois_annee());
            req.setInt(4, cm.getStatus_cra_id());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(CraMois cm) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE CRA_MOIS SET MISSION_ID=?, CONSULTANT_ID=?, MOIS_ANNEE=?, STATUS_ID=? WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, cm.getMission_id());
            req.setInt(2, cm.getConsultant_id());
            req.setDate(3, cm.getMois_annee());
            req.setInt(4, cm.getStatus_cra_id());
            req.setInt(5, cm.getId_cra_mois());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id_cra_mois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CRA_MOIS WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, id_cra_mois);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
