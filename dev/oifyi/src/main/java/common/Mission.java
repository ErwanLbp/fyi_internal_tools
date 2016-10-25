package common;

import java.sql.Date;

/**
 * Created by eisti on 25/10/16.
 */
public class Mission {

    private int id;

    private String libelle;

    private Date date_debut;

    private Date date_fin;

    public Mission(int id, String libelle, Date date_debut, Date date_fin){
        this(libelle,date_debut,date_fin);
        this.id=id;
    }

    public Mission(String libelle, Date date_debut, Date date_fin){
        this.libelle = libelle;
        this.date_debut=date_debut;
        this.date_fin=date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
}
