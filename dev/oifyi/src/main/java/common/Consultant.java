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

    private String username; // FIXME contrainte d'unicité sur le login sinon ca fout un vrai bordel...

    private String password;

    //private int role_id;

    //TODO AJouter des champs utiles

    /**
     * Constructeur Consultant avec id
     *
     * @param id
     * @param nom
     * @param prenom
     * @param username
     * @param password
     */
    public Consultant(int id, String nom, String prenom, String username, String password) {
        this(nom, prenom, username, password);
        this.id = id;
    }

    /**
     * Construcuteur consultant sans id
     *
     * @param nom
     * @param prenom
     * @param username
     * @param password
     */
    public Consultant(String nom, String prenom, String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter nom
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter prenom
     *
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter prenom
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

