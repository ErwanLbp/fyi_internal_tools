package dao;

import common.CraJour;
import common.CraMois;
import db.MyConnectorJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>dao CraMoisDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class CraMoisDAO {

    public static CraMois get(int id_cra_mois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, id_cra_mois);
            ResultSet res = req.executeQuery();
            if (res.next())
                return new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CraMois get(int mission_id, int consultant_id, String moisAnnee) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE MISSION_ID=? AND CONSULTANT_ID=? AND MOIS_ANNEE=?")) {
            req.setInt(1, mission_id);
            req.setInt(2, consultant_id);
            req.setDate(3, Date.valueOf(moisAnnee));
            ResultSet res = req.executeQuery();
            if (res.next())
                return new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insert(CraMois cm) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("INSERT INTO CRA_MOIS (MISSION_ID, CONSULTANT_ID, MOIS_ANNEE, STATUS_ID) VALUES (?,?,?,?)")) {
            req.setInt(1, cm.getMission_id());
            req.setInt(2, cm.getConsultant_id());
            req.setDate(3, cm.getMois_annee());
            req.setInt(4, cm.getStatus_cra_id());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(CraMois cm) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE CRA_MOIS SET MISSION_ID=?, CONSULTANT_ID=?, MOIS_ANNEE=?, STATUS_ID=? WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, cm.getMission_id());
            req.setInt(2, cm.getConsultant_id());
            req.setDate(3, cm.getMois_annee());
            req.setInt(4, cm.getStatus_cra_id());
            req.setInt(5, cm.getId_cra_mois());
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id_cra_mois) {
        Connection connection = MyConnectorJDBC.getConnection();

        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("DELETE FROM CRA_MOIS WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, id_cra_mois);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getInsert() {
        return "INSERT INTO CRA_MOIS (MISSION_ID, CONSULTANT_ID, MOIS_ANNEE, STATUS_ID) VALUES (?,?,?,?)";
    }

    public static boolean insertToutMoisTransaction(List<CraMois> lCraMois, Map<CraJour, Integer> mCraJour) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        boolean toutVaBien = true;

        // Déclaration de deux Statement, 1 pour le CraMois et 1 pour le CraJour
        try (PreparedStatement insertCraMois = connection.prepareStatement(CraMoisDAO.getInsert()); PreparedStatement insertCraJour = connection.prepareStatement(CraJourDAO.getInsert())) {

            // Désactivation de l'auto commit pour démarrer la transaction
            connection.setAutoCommit(false);

            // Création d'une Map qui va contenir {idMission -> idCraMois}
            Map<Integer, Integer> mMissionCraMois = new HashMap<>();
            for (CraMois cm : lCraMois) {
                // Remplissage des champs du CraMois
                insertCraMois.setInt(1, cm.getMission_id());
                insertCraMois.setInt(2, cm.getConsultant_id());
                insertCraMois.setDate(3, cm.getMois_annee());
                insertCraMois.setInt(4, cm.getStatus_cra_id());
                if (!(insertCraMois.executeUpdate() == 1))
                    throw new SQLException();
                insertCraMois.clearParameters();

                // Récupération de l'id du CraMois inséré
                CraMois cmRecup = CraMoisDAO.get(cm.getMission_id(), cm.getConsultant_id(), cm.getMois_annee().toString());
                if (cmRecup == null)
                    throw new SQLException();

                // On ajoute l'idMission et l'idCraMois à la map, pour les retrouver après
                mMissionCraMois.put(cmRecup.getMission_id(), cmRecup.getId_cra_mois());
            }

            // Pour chaque ligne de CraJour à insérer, remplissage des champs du CraJour en mettant l'idCraMois en fonction de la map mMissionCraMois
            for (Map.Entry<CraJour, Integer> icj : mCraJour.entrySet()) {
                insertCraJour.setInt(1, mMissionCraMois.get(icj.getValue()));
                insertCraJour.setInt(2, icj.getKey().getJour());
                insertCraJour.setDouble(3, icj.getKey().getTravail());
                if (!(insertCraJour.executeUpdate() == 1))
                    throw new SQLException();
                insertCraJour.clearParameters();
            }

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

    public static List<CraMois> getAll(int id_consultant) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<CraMois> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE CONSULTANT_ID=? ORDER BY MOIS_ANNEE,MISSION_ID DESC")) {
            req.setInt(1, id_consultant);
            ResultSet res = req.executeQuery();
            while (res.next())
                list_res.add(new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id")));
        } catch (SQLException e) {
            e.printStackTrace();
            list_res.clear();
        }
        return list_res;
    }

    public static List<CraMois> getAll(int id_consultant, Date moisAnnee) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<CraMois> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE CONSULTANT_ID=? AND MOIS_ANNEE=? ORDER BY MOIS_ANNEE,MISSION_ID DESC")) {
            req.setInt(1, id_consultant);
            req.setDate(2, moisAnnee);
            ResultSet res = req.executeQuery();
            while (res.next())
                list_res.add(new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id")));
        } catch (SQLException e) {
            e.printStackTrace();
            list_res.clear();
        }
        return list_res;
    }

    public static Map<Integer, Integer> getMapMissionIdCraMois(int id_consultant, Date moisAnnee) {
        List<CraMois> lcm = getAll(id_consultant, moisAnnee);
        Map<Integer, Integer> mii = new HashMap<>();
        for (CraMois cm : lcm) {
            mii.put(cm.getMission_id(), cm.getId_cra_mois());
        }
        return mii;
    }

    public static List<CraMois> getAll(Date moisAnnee) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        List<CraMois> list_res = new ArrayList<>();
        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE MOIS_ANNEE=? ORDER BY MISSION_ID, CONSULTANT_ID DESC")) {
            req.setDate(1, moisAnnee);
            ResultSet res = req.executeQuery();
            while (res.next())
                list_res.add(new CraMois(res.getInt("id_cra_mois"), res.getInt("mission_id"), res.getInt("consultant_id"), res.getDate("mois_annee"), res.getInt("status_id")));
        } catch (SQLException e) {
            e.printStackTrace();
            list_res.clear();
        }
        return list_res;
    }

    public static boolean isInDB(int idCraMois) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("SELECT * FROM CRA_MOIS WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, idCraMois);
            ResultSet res = req.executeQuery();
            if (res.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setStatus(int idCraMois, int newStatut) {
        Connection connection = MyConnectorJDBC.getConnection();
        if (connection == null) throw new RuntimeException("Probleme de connexion à la base de données");

        try (PreparedStatement req = connection.prepareStatement("UPDATE CRA_MOIS SET STATUS_ID=? WHERE ID_CRA_MOIS=?")) {
            req.setInt(1, newStatut);
            req.setInt(2, idCraMois);
            return req.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
