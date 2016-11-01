package dao;

import common.Consultant;
import common.Mission;
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
    public static Mission_Consultant get(int consultant_id, int mission_id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM MISSION_CONSULTANT WHERE CONSULTANT_ID=? AND MISSION_ID=?")) {
            req.setInt(1, consultant_id);
            req.setInt(2, mission_id);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Mission_Consultant(res.getInt("CONSULTANT_ID"),res.getInt("MISSION_ID"), res.getInt("PRIX"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultantsPourUneMission(int mission_id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT co.id_consultant, co.nom, co.prenom, co.username, co.password, co.role_id FROM CONSULTANT co, MISSION_CONSULTANT mc WHERE mc.CONSULANT_ID = co.ID_CONSULTANT AND mc.MISSION_ID=?")) {
            req.setInt(1, mission_id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("co.id_consultant"), res.getString("co.nom"), res.getString("co.prenom"), res.getString("co.username"), res.getString("co.password"), res.getInt("co.role_id")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultantsDisposPourUneMission(int mission_id) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT co.id_consultant, co.nom, co.prenom, co.username, co.password, co.role_id FROM CONSULTANT co, MISSION_CONSULTANT mc WHERE mc.MISSION_ID=? AND co.id_consultant NOT IN (SELECT CONSULTANT_ID FROM MISSION_CONSULTANT WHERE MISSION_ID=?)")) {
            req.setInt(1, mission_id);
            req.setInt(2, mission_id);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("co.id_consultant"), res.getString("co.nom"), res.getString("co.prenom"), res.getString("co.username"), res.getString("co.password"), res.getInt("co.role_id")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static boolean insert(Mission_Consultant mc) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO MISSION_CONSULTANT (CONSULTANT_ID, MISSION_ID, PRIX) VALUES (?,?,?)")) {
            req.setInt(1, mc.getConsultant_id());
            req.setInt(2, mc.getMission_id());
            req.setFloat(3, mc.getPrix());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean update(Mission_Consultant mc) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE MISSION_CONSULTANT SET PRIX=? WHERE CONSULTANT_ID=? AND MISSION_ID=?")) {
            req.setFloat(1, mc.getPrix());
            req.setInt(2, mc.getConsultant_id());
            req.setInt(3, mc.getMission_id());
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
            return false;
        }
    }

}
