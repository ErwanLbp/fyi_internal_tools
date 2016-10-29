package common;

import java.sql.Date;

/**
 * Created by eisti on 29/10/16.
 */
public class Absence {

    private int id_absence;

    private int id_consultant;

    private int id_type_absence;

    private String plus_precision;

    private Date date_debut;

    private Date date_fin;

    private int id_statut_absence;

    private String commentaire;

    public Absence(int id_absence, int id_consultant, int id_type_absence, String plus_precision, Date date_debut, Date date_fin, int id_statut_absence, String commentaire) {
        this.id_absence = id_absence;
        this.id_consultant = id_consultant;
        this.id_type_absence = id_type_absence;
        this.plus_precision = plus_precision;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_statut_absence = id_statut_absence;
        this.commentaire = commentaire;
    }

    public Absence(int id_consultant, int id_type_absence, String plus_precision, Date date_debut, Date date_fin, int id_statut_absence, String commentaire) {
        this.id_consultant = id_consultant;
        this.id_type_absence = id_type_absence;
        this.plus_precision = plus_precision;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_statut_absence = id_statut_absence;
        this.commentaire = commentaire;
    }

    public int getId_absence() {
        return id_absence;
    }

    public void setId_absence(int id_absence) {
        this.id_absence = id_absence;
    }

    public int getId_consultant() {
        return id_consultant;
    }

    public void setId_consultant(int id_consultant) {
        this.id_consultant = id_consultant;
    }

    public int getId_type_absence() {
        return id_type_absence;
    }

    public void setId_type_absence(int id_type_absence) {
        this.id_type_absence = id_type_absence;
    }

    public String getPlus_precision() {
        return plus_precision;
    }

    public void setPlus_precision(String plus_precision) {
        this.plus_precision = plus_precision;
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

    public int getId_statut_absence() {
        return id_statut_absence;
    }

    public void setId_statut_absence(int id_statut_absence) {
        this.id_statut_absence = id_statut_absence;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
