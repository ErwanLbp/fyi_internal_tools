package dao;

import common.Role;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>dao RoleDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 24-10-2016
 */
public class RoleDAO {

    //TODO Javadoc : RoleDAO
    public static Role get(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM role WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Role(res.getInt("id_role"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : RoleDAO
    public static Role get(int id_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM role WHERE id_role=?")) {
            req.setInt(1, id_role);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Role(res.getInt("id_role"), res.getString("libelle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : RoleDAO
    public static List<Role> getAll() {

        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<Role> list_res = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM role ORDER BY ID_ROLE DESC");
            while (res.next())
                list_res.add(new Role(res.getInt("id_role"), res.getString("libelle")));
        } catch (SQLException e) {
            e.printStackTrace();
            list_res.clear();
        }
        return list_res;
    }

    //TODO Javadoc : RoleDAO
    public static boolean insert(Role role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO role (id_role, libelle) VALUES(?,?)")) {
            req.setInt(1, role.getId_role());
            req.setString(2, role.getLibelle());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : RoleDAO
    public static boolean update(Role role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE role SET LIBELLE=? WHERE id_role=?")) {
            req.setString(1, role.getLibelle());
            req.setInt(2, role.getId_role());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : RoleDAO
    public static boolean delete(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM role WHERE lower(LIBELLE)=lower(?)")) {
            req.setString(1, libelle);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : RoleDAO
    public static boolean isInDB(int role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM ROLE WHERE ID_ROLE=?")) {
            req.setInt(1, role);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
