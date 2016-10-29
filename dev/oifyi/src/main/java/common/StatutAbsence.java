package common;

/**
 * Created by eisti on 29/10/16.
 */
public class StatutAbsence {

    private int id_statut_absence;

    private String libelle;

    public StatutAbsence(int id_statut_absence, String libelle) {
        this.id_statut_absence = id_statut_absence;
        this.libelle = libelle;
    }

    public int getId_statut_absence() {
        return id_statut_absence;
    }

    public void setId_statut_absence(int id_statut_absence) {
        this.id_statut_absence = id_statut_absence;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
