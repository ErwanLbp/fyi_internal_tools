package common;

/**
 * <h1>common Consultant</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 21-10-2016
 */
public class Consultant {

    private int id;

    private String nom;

    private String prenom;

    private String username;

    private String password;

    public Consultant() {
        this(nom, prenom, username, password);
        this.id = id;
    }

    public Consultant(String nom, String prenom, String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

