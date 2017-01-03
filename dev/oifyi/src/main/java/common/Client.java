package common;

//TODO Javadoc : Client
public class Client {

    private int id;

    private String raison_sociale;

    private String forme_juridique;

    private String siret;

    private String num_tva;

    private Adresse adresse;

    /**
     * Constructeur Client avec l'id
     * @param id
     * @param raison_sociale
     * @param forme_juridique
     * @param siret
     * @param num_tva
     * @param adresse
     */
    public Client(int id, String raison_sociale, String forme_juridique, String siret, String num_tva, Adresse adresse) {
        this(raison_sociale, forme_juridique, siret, num_tva, adresse);
        this.id = id;
    }

    /**
     * Constructeur Client sans l'id
     * @param raison_sociale
     * @param forme_juridique
     * @param siret
     * @param num_tva
     * @param adresse
     */
    public Client(String raison_sociale, String forme_juridique, String siret, String num_tva, Adresse adresse) {
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
        this.siret = siret;
        this.num_tva = num_tva;
        this.adresse = adresse;
    }

    /**
     * Getter id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter raison_sociale
     * @return raison_sociale
     */
    public String getRaison_sociale() {
        return raison_sociale;
    }

    /**
     * Setter raison_sociale
     * @param raison_sociale
     */
    public void setRaison_sociale(String raison_sociale) {
        this.raison_sociale = raison_sociale;
    }

    /**
     * Getter forme_juridique
     * @return forme_juridique
     */
    public String getForme_juridique() {
        return forme_juridique;
    }

    /**
     * Setter forme_juridique
     * @param forme_juridique
     */
    public void setForme_juridique(String forme_juridique) {
        this.forme_juridique = forme_juridique;
    }

    /**
     * Getter siret
     * @return siret
     */
    public String getSiret() {
        return siret;
    }

    /**
     * Setter siret
     * @param siret
     */
    public void setSiret(String siret) {
        this.siret = siret;
    }

    /**
     * Getter numv_tva
     * @return num_tva
     */
    public String getNum_tva() {
        return num_tva;
    }

    /**
     * Setter num_tva
     * @param num_tva
     */
    public void setNum_tva(String num_tva) {
        this.num_tva = num_tva;
    }

    /**
     * Getter adresse
     * @return adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Setter adresse
     * @param adresse
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
