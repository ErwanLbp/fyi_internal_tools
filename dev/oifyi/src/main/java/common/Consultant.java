package main.java.common;

/**
 * <h1>main.java Consultant</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 11-10-2016
 */
public class Consultant {

    private Integer id;
    private String nom;
    private String prenom;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
