package dao;

import db.MyConnectorJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwan LBP
 * @version 1.0
 * @since 06-01-2017
 */
public class DocumentLinkDAO {

    public static boolean addDocumentLink(int idConsultant, String name, String absolutePath) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO CVS_CONSULTANT (ID_CONSULTANT, NOM_FICHIER, CHEMIN) VALUES (?,?,?)")) {
            req.setInt(1, idConsultant);
            req.setString(2, name);
            req.setString(3, absolutePath);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getAll(int idConsultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<String> liste_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CVS_CONSULTANT WHERE ID_CONSULTANT=?")) {
            req.setInt(1, idConsultant);
            ResultSet res = req.executeQuery();
            while (res.next())
                liste_res.add(res.getString("chemin"));
        } catch (SQLException e) {
            e.printStackTrace();
            liste_res.clear();
        }
        return liste_res;
    }

}
