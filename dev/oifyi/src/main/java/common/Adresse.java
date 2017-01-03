package common;

/**
 * <h1>common Adresse</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 21-10-2016
 */
public class Adresse {

    private int numero;

    private String rue;

    private int cp;

    private String ville;

    /**
     * Constructeur Adresse
     * @param numero
     * @param rue
     * @param cp
     * @param ville
     */
    public Adresse(int numero, String rue, int cp, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    /**
     * Getter numero
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Setter numero
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Getter rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Setter rue
     * @param rue
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * Getter cp
     * @return
     */
    public int getCp() {
        return cp;
    }

    /**
     * Setter cp
     * @param cp
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * Getter ville
     * @return
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter ville
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }
}
