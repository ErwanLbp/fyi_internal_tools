package dao;

import common.Consultant;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;

import static oracle.net.aso.C03.e;

/**
 * <h1>dao ConsultantDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 18-10-2016
 */
public class ConsultantDAO {

    public static boolean checkLoginPassword(String login, String password) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return false;

        try (PreparedStatement req = connection.prepareStatement("SELECT username, password FROM CONSULTANT WHERE USERNAME=? AND password=?")) {
            req.setString(1, login);
            req.setString(2, password);
            ResultSet res = req.executeQuery();
            if (res.next() && !res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Consultant getById(int i) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return false;

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE id=?")) {
            req.setString(1, i);
            ResultSet res = req.executeQuery();
            res.next()
            Consultant consultant = new Consultant(res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"));
            return consultant;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Consultant> getAll() throws SQLException {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) return null;
        try (Statement req = connection.createStatement() {
            ResultSet res = req.executeQuery("SELECT * FROM individu");
            ArrayList<Consultant> listeConsultants = new ArrayList<Consultant>();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password")));
            }
            return listeConsultants;
        } catch (Exception e) {
            System.out.println("Erreur de findAll");
            return null;
        }
    }
}


}
