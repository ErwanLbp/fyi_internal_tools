package dao;

import common.Mission;
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
public class MissionDAO {

    //TODO Javadoc : MissionDAO
    public static Mission getById(int i) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION WHERE ID=?")) {
            req.setInt(1, i);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Mission(res.getInt("ID"), res.getString("libelle"), res.getDate("date_debut") , res.getDate("date_fin"));
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
                listeMissions.add(new Mission(res.getInt("ID"), res.getString("libelle"), res.getDate("date_debut") , res.getDate("date_fin")));
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
                return new Mission(res.getInt("ID"), res.getString("libelle"), res.getDate("date_debut") , res.getDate("date_fin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : MissionDAO
    public static boolean insert(Mission mission) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO Mission (ID, LIBELLE, DATE_DEBUT, DATE_FIN) VALUES (?,?,?,?)")) {
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

        try (PreparedStatement req = connection.prepareStatement("UPDATE mission SET libelle = ?,date_debut = ?, date_fin=? WHERE ID=?")) {
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

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM mission WHERE ID=?")) {
            req.setInt(1, id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}