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
        if (connection == null) return null;
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT WHERE id=?")) {
            req.setInt(1, i);
            ResultSet res = req.executeQuery();
            res.next();
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
        try (Statement req = connection.createStatement()) {
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

    public static void createConsultant(Consultant consultant){
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection != null){
            try(PreparedStatement req = connection.prepareStatement("INSERT INTO consultant VALUES (?,?,?,?,?)")){
                req.setInt(1, consultant.getId());
                req.setString(2, consultant.getNom());
                req.setString(3, consultant.getPrenom());
                req.setString(4, consultant.getUsername());
                req.setString(5, consultant.getPassword());
                if (req.executeUpdate()==1){
                    System.out.println("L'insertion est réussie.");
                }
                else{
                    System.out.println("L'insertion est ratée.");
                }
            }
            catch (Exception e){
                System.out.println("Erreur de createIndividu");
                e.printStackTrace();
            }
        }
    }

    public static void updateConsultant(Consultant consultant){
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection != null){
            try(PreparedStatement req = connection.prepareStatement("UPDATE consultant SET nom = ?,prenom = ?, username = ?, password = ? WHERE num_ind=?")){
                req.setString(1, consultant.getNom());
                req.setString(2, consultant.getPrenom());
                req.setString(3, consultant.getUsername());
                req.setString(4, consultant.getPassword());
                req.setInt(5, consultant.getId());
                if (req.executeUpdate()==1){
                    System.out.println("La MAJ est réussie.");
                }
                else{
                    System.out.println("La MAJ est ratée.");
                }
            }
            catch (Exception e){
                System.out.println("Erreur de updateIndividu");
                e.printStackTrace();
            }
        }
    }

    public static void deleteIndividuById(int i){
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection != null){
            try(PreparedStatement req = connection.prepareStatement("DELETE FROM consultant WHERE num_ind=?")){
                req.setInt(1, i);
                if (req.executeUpdate()==1){
                    System.out.println("La suppresion est réussie.");
                }
                else{
                    System.out.println("La suppression est ratée.");
                }
            }
            catch (Exception e){
                System.out.println("Erreur de deleteIndividu");
                e.printStackTrace();
            }
        }
    }


}
