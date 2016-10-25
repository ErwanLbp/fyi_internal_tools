package dao;

import common.Mission;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>dao ConsultantDAO</h1>
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 25-10-2016
 */
public class MissionDAO {

    //TODO Javadoc : MissionDAO
    public static Mission get(int id_mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION WHERE ID_MISSION=?")) {
            req.setInt(1, id_mission);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Mission(res.getInt("id_mission"), res.getString("nom"), res.getString("numero_contrat"), res.getInt("client_id"), res.getDate("date_debut"), res.getDate("date_fin"), res.getInt("tjm"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : MissionDAO
    public static Mission get(String nom_mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION WHERE NOM=?")) {
            req.setString(1, nom_mission);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Mission(res.getInt("id_mission"), res.getString("nom"), res.getString("numero_contrat"), res.getInt("client_id"), res.getDate("date_debut"), res.getDate("date_fin"), res.getInt("tjm"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : MissionDAO
    public static boolean insert(Mission mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO MISSION (NOM, NUMERO_CONTRAT, CLIENT_ID, DATE_DEBUT, DATE_FIN, TJM) VALUES (?,?,?,?,?,?)")) {
            req.setString(1, mission.getNom());
            req.setString(2, mission.getNumero_contrat());
            req.setInt(3, mission.getClient_id());
            req.setDate(4, mission.getDate_debut());
            req.setDate(5, mission.getDate_fin());
            req.setInt(6, mission.getTjm());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MissionDAO
    public static boolean update(Mission mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE mission SET NOM=?,NUMERO_CONTRAT=?,CLIENT_ID=?,DATE_DEBUT=?, DATE_FIN=?, TJM=? WHERE ID_MISSION=?")) {
            req.setString(1, mission.getNom());
            req.setString(2, mission.getNumero_contrat());
            req.setInt(3, mission.getClient_id());
            req.setDate(4, mission.getDate_debut());
            req.setDate(5, mission.getDate_fin());
            req.setInt(6, mission.getTjm());
            req.setInt(7, mission.getId_mission());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MissionDAO
    public static boolean delete(int id_mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM MISSION WHERE ID_MISSION=?")) {
            req.setInt(1, id_mission);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MissionDAO
    public static boolean delete(String nom_mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM MISSION WHERE NOM=?")) {
            req.setString(1, nom_mission);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MissionDAO
    public static List<Mission> getMissionsDuConsultant(int id_consultant, Date moisAnnee) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<Mission> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION m, MISSION_CONSULTANT mc WHERE mc.CONSULTANT_ID=? AND m.ID_MISSION=mc.MISSION_ID AND m.DATE_DEBUT<=? AND m.DATE_FIN>=?")) {
            req.setInt(1, id_consultant);
            req.setDate(2, moisAnnee);
            req.setDate(3, moisAnnee); //FIXME à changer pour mettre la fin du mois
            ResultSet res = req.executeQuery();
            while (res.next())
                list_res.add(new Mission(res.getInt("ID_MISSION"), res.getString("nom"), res.getString("numero_contrat"), res.getInt("client_id"), res.getDate("date_debut"), res.getDate("date_fin"), res.getInt("tjm")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_res;
    }
}
