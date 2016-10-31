package dao;

import common.StatusCra;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>dao StatusCraDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class StatusCraDAO {

    //TODO Javadoc : StatusCraDAO
    public static StatusCra get(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM status_cra WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new StatusCra(res.getInt("id_status_cra"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : StatusCraDAO
    public static StatusCra get(int id_status_cra) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM status_cra WHERE id_status_cra=?")) {
            req.setInt(1, id_status_cra);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new StatusCra(res.getInt("id_status_cra"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : StatusCraDAO
    public static List<StatusCra> getAll() {

        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<StatusCra> list_res = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM status_cra");
            while (res.next())
                list_res.add(new StatusCra(res.getInt("id_status_cra"), res.getString("libelle")));
        } catch (SQLException e) {
            e.printStackTrace();
            list_res.clear();
        }
        return list_res;
    }

    //TODO Javadoc : StatusCraDAO
    public static boolean insert(StatusCra st_cra) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO STATUS_CRA (id_status_cra, libelle) VALUES(?,?)")) {
            req.setInt(1, st_cra.getId_status_cra());
            req.setString(2, st_cra.getLibelle());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : StatusCraDAO
    public static boolean update(StatusCra st_cra) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE STATUS_CRA SET LIBELLE=? WHERE id_status_cra=?")) {
            req.setString(1, st_cra.getLibelle());
            req.setInt(2, st_cra.getId_status_cra());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : StatusCraDAO
    public static boolean delete(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM status_cra WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
