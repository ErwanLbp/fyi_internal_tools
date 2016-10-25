package common;

/**
 * Created by croute on 25/10/16.
 */
public class Client {

    private int id;

    private String raison_sociale;

    private String forme_juridique;

    private String siret;

    private String num_tva;

    private String rcs;

    private int adresse_numero;

    private String adresse_rue;

    private int adresse_cp;

    private String adresse_ville;

    private String telephone;

    private int capital;

    private String ville_inscription;

    private String representant_nom;

    private String representant_fonction;

    private String respo_client_tel;

    private String contact_achats_nom;

    private String contact_achats_tel;

    private String respo_fournisseur_nom;

    private String respo_fournisseur_fonction;


    public Client(int id, String raison_sociale, String forme_juridique, String siret, String num_tva, String rcs, int adresse_numero, String adresse_rue, int adresse_cp, String adresse_ville, String telephone, int capital, String ville_inscription, String representant_nom, String representant_fonction, String respo_client_tel, String contact_achats_nom, String contact_achats_tel, String respo_fournisseur_nom) {
        this.id = id;
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
        this.siret = siret;
        this.num_tva = num_tva;
        this.rcs = rcs;
        this.adresse_numero = adresse_numero;
        this.adresse_rue = adresse_rue;
        this.adresse_cp = adresse_cp;
        this.adresse_ville = adresse_ville;
        this.telephone = telephone;
        this.capital = capital;
        this.ville_inscription = ville_inscription;
        this.representant_nom = representant_nom;
        this.representant_fonction = representant_fonction;
        this.respo_client_tel = respo_client_tel;
        this.contact_achats_nom = contact_achats_nom;
        this.contact_achats_tel = contact_achats_tel;
        this.respo_fournisseur_nom = respo_fournisseur_nom;
    }

    public Client(int id, String raison_sociale, String forme_juridique) {
        this.id = id;
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
    }

    public Client(String raison_sociale, String forme_juridique) {
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
    }

    public Client(int id, String forme_juridique) {
        this.id = id;
        this.forme_juridique = forme_juridique;
    }

    public Client(String forme_juridique) {
        this.forme_juridique = forme_juridique;
    }

    public Client(String raison_sociale, String forme_juridique, String siret, String num_tva, String rcs, int adresse_numero, String adresse_rue, int adresse_cp, String adresse_ville, String telephone, int capital, String ville_inscription, String representant_nom, String representant_fonction, String respo_client_tel, String contact_achats_nom, String contact_achats_tel, String respo_fournisseur_nom) {
        this.raison_sociale = raison_sociale;
        this.forme_juridique = forme_juridique;
        this.siret = siret;
        this.num_tva = num_tva;
        this.rcs = rcs;
        this.adresse_numero = adresse_numero;
        this.adresse_rue = adresse_rue;
        this.adresse_cp = adresse_cp;
        this.adresse_ville = adresse_ville;
        this.telephone = telephone;
        this.capital = capital;
        this.ville_inscription = ville_inscription;
        this.representant_nom = representant_nom;
        this.representant_fonction = representant_fonction;
        this.respo_client_tel = respo_client_tel;
        this.contact_achats_nom = contact_achats_nom;
        this.contact_achats_tel = contact_achats_tel;
        this.respo_fournisseur_nom = respo_fournisseur_nom;
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

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
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

    public void setAdresse_cp(int code_postal) {
        this.adresse_cp = code_postal;
    }

    public String getAdresse_ville() {
        return adresse_ville;
    }

    public void setAdresse_ville(String adresse_ville) {
        this.adresse_ville = adresse_ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getVille_inscription() {
        return ville_inscription;
    }

    public void setVille_inscription(String ville_inscription) {
        this.ville_inscription = ville_inscription;
    }

    public String getRepresentant_nom() {
        return representant_nom;
    }

    public void setRepresentant_nom(String representant_nom) {
        this.representant_nom = representant_nom;
    }

    public String getRepresentant_fonction() {
        return representant_fonction;
    }

    public void setRepresentant_fonction(String representant_fonction) {
        this.representant_fonction = representant_fonction;
    }

    public String getRespo_client_tel() {
        return respo_client_tel;
    }

    public void setRespo_client_tel(String respo_client_tel) {
        this.respo_client_tel = respo_client_tel;
    }

    public String getContact_achats_nom() {
        return contact_achats_nom;
    }

    public void setContact_achats_nom(String contact_achats_nom) {
        this.contact_achats_nom = contact_achats_nom;
    }

    public String getContact_achats_tel() {
        return contact_achats_tel;
    }

    public void setContact_achats_tel(String contact_achats_tel) {
        this.contact_achats_tel = contact_achats_tel;
    }

    public String getRespo_fournisseur_nom() {
        return respo_fournisseur_nom;
    }

    public void setRespo_fournisseur_nom(String respo_fournisseur_nom) {
        this.respo_fournisseur_nom = respo_fournisseur_nom;
    }

    public String getRespo_fournisseur_fonction() {
        return respo_fournisseur_fonction;
    }

    public void setRespo_fournisseur_fonction(String respo_fournisseur_fonction) {
        this.respo_fournisseur_fonction = respo_fournisseur_fonction;
    }
}
