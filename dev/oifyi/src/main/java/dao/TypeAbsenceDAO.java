package dao;

import common.TypeAbsence;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>dao TypeAbsenceDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 24-10-2016
 */
public class TypeAbsenceDAO {

    //TODO Javadoc : TypeAbsenceDAO
    public static TypeAbsence get(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM type_absence WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new TypeAbsence(res.getInt("id_typeAbsence"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : TypeAbsenceDAO
    public static TypeAbsence get(int id_type_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM type_absence WHERE id_type_absence=?")) {
            req.setInt(1, id_type_absence);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new TypeAbsence(res.getInt("id_type_absence"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : TypeAbsenceDAO
    public static List<TypeAbsence> getAll() {

        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<TypeAbsence> list_res = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM type_absence ORDER BY ID_type_absence");
            while (res.next())
                list_res.add(new TypeAbsence(res.getInt("id_type_absence"), res.getString("libelle")));
            return list_res;
        } catch (SQLException e) {
            e.printStackTrace();
            return list_res;
        }
    }

    //TODO Javadoc : TypeAbsenceDAO
    public static boolean insert(TypeAbsence type_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO type_absence (id_type_absence, libelle) VALUES(?,?)")) {
            req.setInt(1, type_absence.getId_type_absence());
            req.setString(2, type_absence.getLibelle());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : TypeAbsenceDAO
    public static boolean update(TypeAbsence type_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE type_absence SET LIBELLE=? WHERE id_type_absence=?")) {
            req.setString(1, type_absence.getLibelle());
            req.setInt(2, type_absence.getId_type_absence());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : TypeAbsenceDAO
    public static boolean delete(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM type_absence WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : TypeAbsenceDAO
    public static boolean isInDB(int id_type_absence) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM type_absence WHERE ID_type_absence=?")) {
            req.setInt(1, id_type_absence);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
