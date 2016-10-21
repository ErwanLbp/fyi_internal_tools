package common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <h1>common Adresse</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 21-10-2016
 */
@Embeddable
public class Adresse implements Serializable {

    @Column(name = "ADRESSE_NUMERO", nullable = false)
    private int adresseNumero;

    @Column(name = "ADRESSE_RUE", nullable = false)
    private String adresseRue;

    @Column(name = "ADRESSE_CP", nullable = false)
    private int adresseCp;

    @Column(name = "ADRESSE_VILLE", nullable = false)
    private String adresseVille;

    public Adresse(int adresseNumero, String adresseRue, int adresseCp, String adresseVille) {
        this.adresseNumero = adresseNumero;
        this.adresseRue = adresseRue;
        this.adresseCp = adresseCp;
        this.adresseVille = adresseVille;
    }

    public Adresse() {
        this(0, "", 0, "");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresse)) return false;

        Adresse adresse = (Adresse) o;

        if (getAdresseNumero() != adresse.getAdresseNumero()) return false;
        if (getAdresseCp() != adresse.getAdresseCp()) return false;
        if (!getAdresseRue().equals(adresse.getAdresseRue())) return false;
        return getAdresseVille().equals(adresse.getAdresseVille());

    }

    @Override
    public int hashCode() {
        int result = getAdresseNumero();
        result = 31 * result + getAdresseRue().hashCode();
        result = 31 * result + getAdresseCp();
        result = 31 * result + getAdresseVille().hashCode();
        return result;
    }
}
