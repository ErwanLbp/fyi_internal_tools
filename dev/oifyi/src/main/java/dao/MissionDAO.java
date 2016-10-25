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
    public static Mission getById(int i) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION WHERE ID_MISSION=?")) {
            req.setInt(1, i);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Mission(res.getInt("ID_MISSION"), res.getString("libelle"), res.getDate("date_debut"), res.getDate("date_fin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : MissionDAO
    public static ArrayList<Mission> getAll() throws SQLException {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Mission> listeMissions = new ArrayList<Mission>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM MISSION");
            while (res.next())
                listeMissions.add(new Mission(res.getInt("ID_MISSION"), res.getString("libelle"), res.getDate("date_debut"), res.getDate("date_fin")));
            return listeMissions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeMissions;
    }


    //TODO Javadoc : MissionDAO
    public static Mission getByLibelleDateDebut(String libelle, Date date_debut) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION WHERE libelle=? AND date_debut=?")) {
            req.setString(1, libelle);
            req.setDate(2, date_debut);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Mission(res.getInt("ID_MISSION"), res.getString("libelle"), res.getDate("date_debut"), res.getDate("date_fin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : MissionDAO
    public static boolean insert(Mission mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO Mission (ID_MISSION, LIBELLE, DATE_DEBUT, DATE_FIN) VALUES (?,?,?,?)")) {
            req.setInt(1, mission.getId());
            req.setString(2, mission.getLibelle());
            req.setDate(3, (Date) mission.getDate_debut());
            req.setDate(4, (Date) mission.getDate_fin());
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

        try (PreparedStatement req = connection.prepareStatement("UPDATE mission SET libelle = ?,date_debut = ?, date_fin=? WHERE ID_MISSION=?")) {
            req.setString(1, mission.getLibelle());
            req.setDate(2, (Date) mission.getDate_debut());
            req.setDate(3, (Date) mission.getDate_fin());
            req.setInt(4, mission.getId());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : MissionDAO
    public static boolean deleteById(int id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM mission WHERE ID_MISSION=?")) {
            req.setInt(1, id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
                list_res.add(new Mission(res.getInt("ID_MISSION"), res.getString("libelle"), res.getDate("date_debut"), res.getDate("date_fin")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_res;
    }
}
