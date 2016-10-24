package dao;

import db.MyConnectorJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
