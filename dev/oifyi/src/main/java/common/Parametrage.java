package common;

/**
 * Created by eisti on 28/10/16.
 */
public class Parametrage {

    private String libelle;

    private String valeur;

    public Parametrage(String valeur) {
        this.valeur = valeur;
    }

    public Parametrage(String libelle, String valeur) {
        this.libelle = libelle;
        this.valeur = valeur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
