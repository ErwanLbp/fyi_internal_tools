package common;

import java.sql.Date;

/**
 * Created by Croute on 27/10/16.
 */
public class CraJour {

    private int id_cramois;

    private Date jour;

    private double heures_travail;

    public CraJour(int id_cramois, Date jour, double heures_travail) {
        this.id_cramois = id_cramois;
        this.jour = jour;
        this.heures_travail = heures_travail;
    }

    public int getId_cramois() {
        return id_cramois;
    }

    public void setId_cramois(int id_cramois) {
        this.id_cramois = id_cramois;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public double getHeures_travail() {
        return heures_travail;
    }

    public void setHeures_travail(double heures_travail) {
        this.heures_travail = heures_travail;
    }
}
