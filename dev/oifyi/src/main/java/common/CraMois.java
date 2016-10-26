package common;

import java.sql.Date;

/**
 * <h1>common CraMois</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class CraMois {

    private int id_cra_mois;

    private int mission_id;

    private int consultant_id;

    private Date mois_annee;

    private int status_cra_id;

    public CraMois(int id_cra_mois, int mission_id, int consultant_id, Date mois_annee, int status_cra_id) {
        this(mission_id, consultant_id, mois_annee, status_cra_id);
        this.id_cra_mois = id_cra_mois;
    }

    public CraMois(int mission_id, int consultant_id, Date mois_annee, int status_cra_id) {
        this.mission_id = mission_id;
        this.consultant_id = consultant_id;
        this.mois_annee = mois_annee;
        this.status_cra_id = status_cra_id;
    }

    public int getId_cra_mois() {
        return id_cra_mois;
    }

    public void setId_cra_mois(int id_cra_mois) {
        this.id_cra_mois = id_cra_mois;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public int getConsultant_id() {
        return consultant_id;
    }

    public void setConsultant_id(int consultant_id) {
        this.consultant_id = consultant_id;
    }

    public Date getMois_annee() {
        return mois_annee;
    }

    public void setMois_annee(Date mois_annee) {
        this.mois_annee = mois_annee;
    }

    public int getStatus_cra_id() {
        return status_cra_id;
    }

    public void setStatus_cra_id(int status_cra_id) {
        this.status_cra_id = status_cra_id;
    }
}
