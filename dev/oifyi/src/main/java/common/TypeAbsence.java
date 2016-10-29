package common;

/**
 * Created by eisti on 29/10/16.
 */
public class TypeAbsence {

    private int id_type_absence;

    private String libelle;

    public TypeAbsence(int id_type_absence, String libelle) {
        this.id_type_absence = id_type_absence;
        this.libelle = libelle;
    }

    public int getId_type_absence() {
        return id_type_absence;
    }

    public void setId_type_absence(int id_type_absence) {
        this.id_type_absence = id_type_absence;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
