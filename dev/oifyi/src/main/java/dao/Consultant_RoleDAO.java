package dao;

import common.Consultant;
import common.Consultant_Role;
import common.Role;
import db.MyConnectorJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eisti on 31/10/16.
 */
public class Consultant_RoleDAO {

/*    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultants(int id_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT co.id_consultant, co.nom, co.prenom, co.username, co.password, co.role_id FROM CONSULTANT co, CONSULTANT_ROLE cr WHERE cr.ID_CONSULTANT = cr.ID_CONSULTANT AND cr.ID_ROLE=?")) {
            req.setInt(1, id_role);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password"), res.getInt("role_id")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }*/

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultants(int id_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT co.id_consultant, co.nom, co.prenom, co.username, co.password FROM CONSULTANT co, CONSULTANT_ROLE cr WHERE co.ID_CONSULTANT = cr.ID_CONSULTANT AND cr.ID_ROLE=?")) {
            req.setInt(1, id_role);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Consultant> getConsultants(String libelle_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Consultant> listeConsultants = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT co.id_consultant, co.nom, co.prenom, co.username, co.password FROM CONSULTANT co, CONSULTANT_ROLE cr, ROLE ro WHERE co.ID_CONSULTANT = cr.ID_CONSULTANT AND cr.ID_ROLE=ro.ID_ROLE AND lower(ro.LIBELLE)=?")) {
            req.setString(1, libelle_role);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeConsultants.add(new Consultant(res.getInt("id_consultant"), res.getString("nom"), res.getString("prenom"), res.getString("username"), res.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeConsultants;
    }

    //TODO Javadoc : Mission_ConsultantDAO
    public static ArrayList<Role> getRoles(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Role> listeRoles = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT ro.ID_ROLE , ro.LIBELLE FROM ROLE ro, CONSULTANT_ROLE cr WHERE cr.ID_CONSULTANT=? AND ro.ID_ROLE=cr.ID_ROLE")) {
            req.setInt(1, id_consultant);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeRoles.add(new Role(res.getInt("ID_ROLE"), res.getString("LIBELLE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeRoles;
    }

    //TODO Javadoc : Consultant_RoleDAO
    public static ArrayList<Role> getRolesDisposPourUnConsultant(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");
        ArrayList<Role> listeRoles = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT ro.ID_ROLE, ro.LIBELLE FROM ROLE ro, CONSULTANT_ROLE cr WHERE cr.ID_CONSULTANT=? AND ro.ID_ROLE NOT IN (SELECT ID_ROLE FROM CONSULTANT_ROLE WHERE CONSULTANT_ID=?)")) {
            req.setInt(1, id_consultant);
            req.setInt(2, id_consultant);
            ResultSet res = req.executeQuery();
            while (res.next()) {
                listeRoles.add(new Role(res.getInt("ID_ROLE"), res.getString("LIBELLE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeRoles;
    }


    //TODO Javadoc : Mission_ConsultantDAO
    public static boolean insert(Consultant_Role cr) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO CONSULTANT_ROLE (ID_CONSULTANT, ID_ROLE) VALUES (?,?)")) {
            req.setInt(1, cr.getId_consultant());
            req.setInt(2, cr.getId_role());
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //TODO Javadoc : Consultant_MissionDAO
    public static boolean delete(int id_consultant, int id_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CONSULTANT_ROLE WHERE ID_CONSULTANT=? AND ID_ROLE=?")) {
            req.setInt(1, id_consultant);
            req.setInt(2, id_role);
            return req.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO Javadoc : Consultant_MissionDAO
    public static int delete(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CONSULTANT_ROLE WHERE ID_CONSULTANT=?")) {
            req.setInt(1, id_consultant);
            return req.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isInDB(int id_consultant, int id_role) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CONSULTANT_ROLE WHERE ID_CONSULTANT=? AND ID_ROLE=?")) {
            req.setInt(1, id_consultant);
            req.setInt(2, id_role);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getInsert() {
        return "INSERT INTO CONSULTANT_ROLE (ID_CONSULTANT, ID_ROLE) VALUES (?,?)";
    }

    public static boolean insertConsultantEtTousRolesTransaction(Consultant consultant, List<Integer> l_id_roles) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        boolean toutVaBien = true;

        // Déclaration de du Statement pour l'insert du consultant_role
        try (PreparedStatement req = connection.prepareStatement(Consultant_RoleDAO.getInsert())) {

            // Désactivation de l'auto commit pour démarrer la transaction
            connection.setAutoCommit(false);

            if (consultant.getId() == -1) {
                if (!ConsultantDAO.insert(consultant))
                    throw new SQLException("Erreur lors de l'insertion du consultant");
            } else {
                if (!ConsultantDAO.update(consultant))
                    throw new SQLException("Erreur lors de l'update du consultant");
            }
            System.out.println("---");

            int id_consultant = ConsultantDAO.get(consultant.getUsername()).getId();
            System.out.println("ok get Consultant : " + id_consultant);
            if (consultant.getId() != -1 && Consultant_RoleDAO.delete(id_consultant) < 1) throw new SQLException();
            System.out.println("ok delete");
            for (int id_role : l_id_roles) {
                System.out.println(id_consultant + " " + id_role);
                // Remplissage des champs de Consultant_Role
                req.setInt(1, id_consultant);
                req.setInt(2, id_role);
                if (!(req.executeUpdate() == 1))
                    throw new SQLException();
            }
            System.out.println("ok remplissage champs req");
            // Commit de la transaction pour sauvegarder
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            toutVaBien = false;
            // En cas d'erreur, on annule la transaction
            try {
                connection.rollback();
            } catch (SQLException excep) {
                e.printStackTrace();
                // Tout va super mal
            }
        }

        // On remet l'autocommit pour les requetes futures
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            // Tout va méga mal, l'ordi va péter
        }
        return toutVaBien;
    }
}
