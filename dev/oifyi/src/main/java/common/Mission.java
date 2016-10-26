package common;

import java.sql.Date;

/**
 * <h1>common ConsultantDAO</h1>
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 25-10-2016
 */
public class Mission {

    private int id_mission;

    private String nom;

    private String numero_contrat;

    private int client_id;

    private Date date_debut;

    private Date date_fin;

    private int tjm;

    public Mission(int id_mission, String nom, String numero_contrat, int client_id, Date date_debut, Date date_fin, int tjm) {
        this(nom, numero_contrat, client_id, date_debut, date_fin, tjm);
        this.id_mission = id_mission;
    }

    public Mission(String nom, String numero_contrat, int client_id, Date date_debut, Date date_fin, int tjm) {
        this.nom = nom;
        this.numero_contrat = numero_contrat;
        this.client_id = client_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tjm = tjm;
    }

    public int getId_mission() {
        return id_mission;
    }

    public void setId_mission(int id_mission) {
        this.id_mission = id_mission;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero_contrat() {
        return numero_contrat;
    }

    public void setNumero_contrat(String numero_contrat) {
        this.numero_contrat = numero_contrat;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
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

    public int getTjm() {
        return tjm;
    }

    public void setTjm(int tjm) {
        this.tjm = tjm;
    }
}
