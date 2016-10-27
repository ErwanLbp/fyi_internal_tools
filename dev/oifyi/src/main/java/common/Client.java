package common;

//TODO Javadoc : Client
public class Client {

    private int id;

    private String raison_sociale;

    private String forme_juridique;

    private String siret;

    private String num_tva;

    private Adresse adresse;

    public Client(int id, String raison_sociale, String forme_juridique, String siret, String num_tva, Adresse adresse) {
        this(raison_sociale, forme_juridique, siret, num_tva, adresse);
        this.id = id;
    }

    public Client(String raison_sociale, String forme_juridique, String siret, String num_tva, Adresse adresse) {
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
        this.siret = siret;
        this.num_tva = num_tva;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaison_sociale() {
        return raison_sociale;
    }

    public void setRaison_sociale(String raison_sociale) {
        this.raison_sociale = raison_sociale;
    }

    public String getForme_juridique() {
        return forme_juridique;
    }

    public void setForme_juridique(String forme_juridique) {
        this.forme_juridique = forme_juridique;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getNum_tva() {
        return num_tva;
    }

    public void setNum_tva(String num_tva) {
        this.num_tva = num_tva;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
