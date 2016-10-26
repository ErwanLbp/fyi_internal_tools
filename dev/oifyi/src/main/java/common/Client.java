package common;

//TODO Javadoc : Client
public class Client {

    private int id;

    private String raison_sociale;

    private String forme_juridique;

    private String siret;

    private String num_tva;

    private int adresse_numero;

    private String adresse_rue;

    private int adresse_cp;

    private String adresse_ville;

    public Client(int id, String raison_sociale, String forme_juridique, String siret, String num_tva, int adresse_numero, String adresse_rue, int adresse_cp, String adresse_ville) {
        this(raison_sociale, forme_juridique, siret, num_tva, adresse_numero, adresse_rue, adresse_cp, adresse_ville);
        this.id = id;
    }

    public Client(String raison_sociale, String forme_juridique, String siret, String num_tva, int adresse_numero, String adresse_rue, int adresse_cp, String adresse_ville) {
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
        this.siret = siret;
        this.num_tva = num_tva;
        this.adresse_numero = adresse_numero;
        this.adresse_rue = adresse_rue;
        this.adresse_cp = adresse_cp;
        this.adresse_ville = adresse_ville;
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

    public int getAdresse_numero() {
        return adresse_numero;
    }

    public void setAdresse_numero(int adresse_numero) {
        this.adresse_numero = adresse_numero;
    }

    public String getAdresse_rue() {
        return adresse_rue;
    }

    public void setAdresse_rue(String adresse_rue) {
        this.adresse_rue = adresse_rue;
    }

    public int getAdresse_cp() {
        return adresse_cp;
    }

    public void setAdresse_cp(int adresse_cp) {
        this.adresse_cp = adresse_cp;
    }

    public String getAdresse_ville() {
        return adresse_ville;
    }

    public void setAdresse_ville(String adresse_ville) {
        this.adresse_ville = adresse_ville;
    }
}
