package dao;

import common.Parametrage;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Croute on 28/10/16.
 */
public class ParametrageDAO {

    //TODO Javadoc : ParametrageDAO
    public static Parametrage get(String libelle){
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM PARAMETRAGE WHERE LIBELLE=?")) {
            req.setString(1, libelle);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new Parametrage(res.getString("libelle"), res.getString("valeur"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO Javadoc : ParametrageDAO
    public static ArrayList<Parametrage> getAll(){
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        ArrayList<Parametrage> listeParametrages = new ArrayList<>();
        try (Statement req = connection.createStatement()) {
            ResultSet res = req.executeQuery("SELECT * FROM PARAMETRAGE");
            while (res.next())
                listeParametrages.add(new Parametrage(res.getString("libelle"), res.getString("valeur")));
            return listeParametrages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeParametrages;
    }

    //TODO Javadoc : ParametrageDAO
    public static boolean insert(Parametrage parametrage) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO parametrage(LIBELLE, VALEUR) VALUES (?,?)")) {
            req.setString(1, parametrage.getLibelle());
            req.setString(2, parametrage.getValeur());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ParametrageDAO
    public static boolean update(Parametrage parametrage) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE PARAMETRAGE SET VALEUR=? WHERE LIBELLE=?")) {
            req.setString(1, parametrage.getValeur());
            req.setString(2, parametrage.getLibelle());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Javadoc : ParametrageDAO
    public static boolean delete(String libelle) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM PARAMETRAGE WHERE LIBELLE=?")) {
            req.setString(1, libelle);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
