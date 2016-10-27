package common;

/**
 * <h1>common NewConsultant</h1>
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

    private int role_id;

    //TODO AJouter des champs utiles

    public Consultant(int id, String nom, String prenom, String username, String password, int role_id) {
        this(nom, prenom, username, password, role_id);
        this.id = id;
    }

    public Consultant(String nom, String prenom, String username, String password, int role_id) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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

