package dao;

import common.Absence;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Croute on 29/10/16.
 */
public class AbsenceDAO {

    //TODO Javadoc : AbsenceDAO
    public static Absence get(int id_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM ABSENCE WHERE ID_ABSENCE=?")) {
            req.setInt(1, id_absence);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Absence(res.getInt("ID_ABSENCE"), res.getInt("ID_CONSULTANT"), res.getInt("ID_TYPE_ABSENCE"), res.getString("PLUS_PRECISION"), res.getDate("DATE_DEBUT"), res.getDate("DATE_FIN"), res.getInt("ID_STATUT_ABSENCE"), res.getString("COMMENTAIRE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : AbsenceDAO
    public static Absence get(int id_consultant, Date date_deb, Date date_fin) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM ABSENCE WHERE ID_CONSULTANT=? AND DATE_DEB=? AND DATE_FIN=?")) {
            req.setInt(1, id_consultant);
            req.setDate(2, date_deb);
            req.setDate(3, date_fin);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Absence(res.getInt("ID_ABSENCE"), res.getInt("ID_CONSULTANT"), res.getInt("ID_TYPE_ABSENCE"), res.getString("PLUS_PRECISION"), res.getDate("DATE_DEBUT"), res.getDate("DATE_FIN"), res.getInt("ID_STATUT_ABSENCE"), res.getString("COMMENTAIRE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ConsultantDAO
    public static ArrayList<Absence> getAllForConsultant(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Absence> listeAbsencesConsultant = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM ABSENCE WHERE ID_CONSULTANT=?")) {
            req.setInt(1, id_consultant);
            ResultSet res = req.executeQuery();
            while (res.next())
                listeAbsencesConsultant.add(new Absence(res.getInt("ID_ABSENCE"), res.getInt("ID_CONSULTANT"), res.getInt("ID_TYPE_ABSENCE"), res.getString("PLUS_PRECISION"), res.getDate("DATE_DEBUT"), res.getDate("DATE_FIN"), res.getInt("ID_STATUT_ABSENCE"), res.getString("COMMENTAIRE")));
            return listeAbsencesConsultant;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeAbsencesConsultant;
    }

    //TODO Javadoc : ConsultantDAO
    public static ArrayList<Absence> getAllForConsultantBetween(int id_consultant, Date date_debut_periode, Date date_fin_periode) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Absence> listeAbsencesConsultant = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM ABSENCE WHERE ID_CONSULTANT=? AND DATE_DEBUT>=? AND DATE_FIN<=?")) {
            req.setInt(1, id_consultant);
            req.setDate(2, date_debut_periode);
            req.setDate(3, date_fin_periode);
            ResultSet res = req.executeQuery();
            while (res.next())
                listeAbsencesConsultant.add(new Absence(res.getInt("ID_ABSENCE"), res.getInt("ID_CONSULTANT"), res.getInt("ID_TYPE_ABSENCE"), res.getString("PLUS_PRECISION"), res.getDate("DATE_DEBUT"), res.getDate("DATE_FIN"), res.getInt("ID_STATUT_ABSENCE"), res.getString("COMMENTAIRE")));
        } catch (SQLException e) {
            e.printStackTrace();
            listeAbsencesConsultant.clear();
        }
        return listeAbsencesConsultant;
    }

    //TODO Javadoc : ConsultantDAO
    public static ArrayList<Absence> getAll() {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Absence> listeAbsencesConsultant = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM ABSENCE");
            while (res.next())
                listeAbsencesConsultant.add(new Absence(res.getInt("ID_ABSENCE"), res.getInt("ID_CONSULTANT"), res.getInt("ID_TYPE_ABSENCE"), res.getString("PLUS_PRECISION"), res.getDate("DATE_DEBUT"), res.getDate("DATE_FIN"), res.getInt("ID_STATUT_ABSENCE"), res.getString("COMMENTAIRE")));
        } catch (SQLException e) {
            e.printStackTrace();
            listeAbsencesConsultant.clear();
        }
        return listeAbsencesConsultant;
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean insert(Absence absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO ABSENCE (ID_CONSULTANT, ID_TYPE_ABSENCE, PLUS_PRECISION, DATE_DEBUT, DATE_FIN, ID_STATUT_ABSENCE, COMMENTAIRE) VALUES (?,?,?,?,?,?,?)")) {
            req.setInt(1, absence.getId_consultant());
            req.setInt(2, absence.getId_type_absence());
            req.setString(3, absence.getPlus_precision());
            req.setDate(4, absence.getDate_debut());
            req.setDate(5, absence.getDate_fin());
            req.setInt(6, absence.getId_statut_absence());
            req.setString(7, absence.getCommentaire());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean update(Absence absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE ABSENCE SET ID_CONSULTANT=?,ID_TYPE_ABSENCE=?, PLUS_PRECISION=?, DATE_DEBUT=?, DATE_FIN=?, ID_STATUT_ABSENCE=?, COMMENTAIRE=? WHERE ID_ABSENCE=?")) {
            req.setInt(1, absence.getId_consultant());
            req.setInt(2, absence.getId_type_absence());
            req.setString(3, absence.getPlus_precision());
            req.setDate(4, absence.getDate_debut());
            req.setDate(5, absence.getDate_fin());
            req.setInt(6, absence.getId_statut_absence());
            req.setString(7, absence.getCommentaire());
            req.setInt(8, absence.getId_absence());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean delete(Absence absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM ABSENCE WHERE ID_ABSENCE=?")) {
            req.setInt(1, absence.getId_absence());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean delete(int id_consultant, Date date_deb, Date date_fin) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM ABSENCE WHERE ID_CONSULTANT=? AND DATE_DEBUT=? AND DATE_FIN=?")) {
            req.setInt(1, id_consultant);
            req.setDate(2, date_deb);
            req.setDate(3, date_fin);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean delete(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM ABSENCE WHERE ID_CONSULTANT=?")) {
            req.setInt(1, id_consultant);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : AbsenceDAO
    public static boolean isInDB(int id_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM ABSENCE WHERE ID_ABSENCE=?")) {
            req.setInt(1, id_absence);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
