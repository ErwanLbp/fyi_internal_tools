package dao;

import common.Consultant;
import common.Mission_Consultant;
import db.MyConnectorJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by eisti on 31/10/16.
 */
public class Mission_ConsultantDAO {

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultantsPourUneMission(int mission_id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT co.id_consultant, co.nom, co.prenom, co.username, co.password FROM CONSULTANT co, MISSION_CONSULTANT mc WHERE mc.CONSULTANT_ID = co.ID_CONSULTANT AND mc.MISSION_ID=?")) {
            req.setInt(1, mission_id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            listeConsultants.clear();
        }
        return listeConsultants;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultantsDisposPourUneMission(int mission_id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE ID_CONSULTANT NOT IN (SELECT CONSULTANT_ID FROM MISSION_CONSULTANT WHERE MISSION_ID=?)")) {
            req.setInt(1, mission_id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static boolean insert(Mission_Consultant mc) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO MISSION_CONSULTANT (CONSULTANT_ID, MISSION_ID) VALUES (?,?)")) {
            req.setInt(1, mc.getConsultant_id());
            req.setInt(2, mc.getMission_id());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //TODO Javadoc : Consultant_MissionDAO
    public static boolean delete(int consultant_id, int mission_id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM MISSION_CONSULTANT WHERE CONSULTANT_ID=? AND MISSION_ID=?")) {
            req.setInt(1, consultant_id);
            req.setInt(2, mission_id);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isInDB(int id_mission, int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION_CONSULTANT WHERE MISSION_ID=? AND CONSULTANT_ID=?")) {
            req.setInt(1, id_mission);
            req.setInt(2, id_consultant);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
