package common;

/**
 * Created by Croute on 27/10/16.
 */
public class CraJour {

    private int id_cramois;

    private int jour;

    private double travail;

    public CraJour(int id_cramois, int jour, double heures_travail) {
        this.id_cramois = id_cramois;
        this.jour = jour;
        this.travail = heures_travail;
    }

    public int getId_cramois() {
        return id_cramois;
    }

    public void setId_cramois(int id_cramois) {
        this.id_cramois = id_cramois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public double getTravail() {
        return travail;
    }

    public void setTravail(double travail) {
        this.travail = travail;
    }
}
