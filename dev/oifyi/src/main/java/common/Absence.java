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

    /**
     * Constructeur avec l'id de absence
     *
     * @param id_absence
     * @param id_consultant
     * @param id_type_absence
     * @param plus_precision
     * @param date_debut
     * @param date_fin
     * @param id_statut_absence
     * @param commentaire
     */
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

    /**
     * Constructeur sans l'id de absence
     *
     * @param id_consultant
     * @param id_type_absence
     * @param plus_precision
     * @param date_debut
     * @param date_fin
     * @param id_statut_absence
     * @param commentaire
     */
    public Absence(int id_consultant, int id_type_absence, String plus_precision, Date date_debut, Date date_fin, int id_statut_absence, String commentaire) {
        this.id_consultant = id_consultant;
        this.id_type_absence = id_type_absence;
        this.plus_precision = plus_precision;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_statut_absence = id_statut_absence;
        this.commentaire = commentaire;
    }

    /**
     * Getter absence
     *
     * @return id_absence
     */
    public int getId_absence() {
        return id_absence;
    }

    /**
     * Setter absence
     *
     * @param id_absence
     */
    public void setId_absence(int id_absence) {
        this.id_absence = id_absence;
    }

    /**
     * Getter id_consultant
     *
     * @return id_consultant
     */
    public int getId_consultant() {
        return id_consultant;
    }

    /**
     * Setter id consultant
     *
     * @param id_consultant
     */
    public void setId_consultant(int id_consultant) {
        this.id_consultant = id_consultant;
    }

    /**
     * Getter id type absence
     *
     * @return id_type_absence
     */
    public int getId_type_absence() {
        return id_type_absence;
    }

    /**
     * Setter id type absence
     *
     * @param id_type_absence
     */
    public void setId_type_absence(int id_type_absence) {
        this.id_type_absence = id_type_absence;
    }

    /**
     * Getter plus_precision
     *
     * @return plus_precision
     */
    public String getPlus_precision() {
        return plus_precision;
    }

    /**
     * Setter plus_precision
     *
     * @param plus_precision
     */
    public void setPlus_precision(String plus_precision) {
        this.plus_precision = plus_precision;
    }

    /**
     * Getter date_debut
     *
     * @return date_debut
     */
    public Date getDate_debut() {
        return date_debut;
    }

    /**
     * Setter date_debut
     *
     * @param date_debut
     */
    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    /**
     * Getter date_fin
     *
     * @return date_fin
     */
    public Date getDate_fin() {
        return date_fin;
    }

    /**
     * Setter date_fin
     *
     * @param date_fin
     */
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    /**
     * Setter id_statut_absence
     *
     * @return id_statut_absence
     */
    public int getId_statut_absence() {
        return id_statut_absence;
    }

    /**
     * Setter id_statut_absence
     *
     * @param id_statut_absence
     */
    public void setId_statut_absence(int id_statut_absence) {
        this.id_statut_absence = id_statut_absence;
    }

    /**
     * Getter commentaire
     *
     * @return commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Setter commentaire
     *
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean estAbsentLeJour(Date date) {
        return date_debut.getTime() <= date.getTime() && date.getTime() <= date_fin.getTime();
    }
}
