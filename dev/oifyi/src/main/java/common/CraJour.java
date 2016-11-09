package common;

/**
 * Created by Croute on 27/10/16.
 */
public class CraJour {

    private int id_cramois;

    private int jour;

    private double travail;

    /**
     * Constructeur CraJour
     * @param id_cramois
     * @param jour
     * @param heures_travail
     */
    public CraJour(int id_cramois, int jour, double heures_travail) {
        this.id_cramois = id_cramois;
        this.jour = jour;
        this.travail = heures_travail;
    }

    /**
     * Getter id_cramois
     * @return id_cramois
     */
    public int getId_cramois() {
        return id_cramois;
    }

    /**
     * Setter id_cramois
     * @param id_cramois
     */
    public void setId_cramois(int id_cramois) {
        this.id_cramois = id_cramois;
    }

    /**
     * Getter jour
     * @return jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * Setter jour
     * @param jour
     */
    public void setJour(int jour) {
        this.jour = jour;
    }

    /**
     * Getter travail
     * @return travail
     */
    public double getTravail() {
        return travail;
    }

    /**
     * Setter travail
     * @param travail
     */
    public void setTravail(double travail) {
        this.travail = travail;
    }
}
