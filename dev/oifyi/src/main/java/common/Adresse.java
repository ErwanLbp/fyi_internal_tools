package common;

import java.io.Serializable;

/**
 * <h1>common Adresse</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 21-10-2016
 */
public class Adresse implements Serializable {

    private int adresseNumero;

    private String adresseRue;

    private int adresseCp;

    private String adresseVille;

    public Adresse(int adresseNumero, String adresseRue, int adresseCp, String adresseVille) {
        this.adresseNumero = adresseNumero;
        this.adresseRue = adresseRue;
        this.adresseCp = adresseCp;
        this.adresseVille = adresseVille;
    }

    public int getAdresseNumero() {
        return adresseNumero;
    }

    public void setAdresseNumero(int adresseNumero) {
        this.adresseNumero = adresseNumero;
    }

    public String getAdresseRue() {
        return adresseRue;
    }

    public void setAdresseRue(String adresseRue) {
        this.adresseRue = adresseRue;
    }

    public int getAdresseCp() {
        return adresseCp;
    }

    public void setAdresseCp(int adresseCp) {
        this.adresseCp = adresseCp;
    }

    public String getAdresseVille() {
        return adresseVille;
    }

    public void setAdresseVille(String adresseVille) {
        this.adresseVille = adresseVille;
    }

}
