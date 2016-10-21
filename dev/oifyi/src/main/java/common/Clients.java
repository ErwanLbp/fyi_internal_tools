package common;

import javax.persistence.*;

/**
 * <h1>common Clients</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 21-10-2016
 */
@Entity
@Table(name = "clients")
public class Clients {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "RAISON_SOCIALE", nullable = false)
    private String raisonSociale;

    @Column(name = "FORME_JURIDIQUE", nullable = false)
    private String formeJuridique;

    @Column(name = "SIRET", nullable = false)
    private String siret;

    @Column(name = "NUM_TVA", nullable = false)
    private String numTva;

    @Column(name = "RCS")
    private String rcs;

    @Embedded
    private Adresse adresse;

    @Column(name = "TELEPHONE")
    private int telephone;

    @Column(name = "CAPITAL")
    private int capital;

    @Column(name = "VILLE_INSCRIPTION")
    private String villeInscription;

    @Column(name = "REPRESENTANT_NOM")
    private String representantNom;

    @Column(name = "REPRESENTANT_FONCTION")
    private String representantFonction;

    @Column(name = "RESPO_CLIENT_TEL")
    private int respoClientTel;

    @Column(name = "CONTACT_ACHATS_NOM")
    private String contactAchatsNom;

    @Column(name = "CONTACT_ACHATS_TEL")
    private int contactAchatsTel;

    @Column(name = "RESPO_FOURNISSEUR_NOM")
    private String respoFournisseurNom;

    @Column(name = "RESPO_FOURNISSEUR_FONCTION")
    private String respoFournisseurFonction;

    public Clients(int id, String raisonSociale, String formeJuridique, String siret, String numTva, String rcs, Adresse adresse, int telephone, int capital, String villeInscription, String representantNom, String representantFonction, int respoClientTel, String contactAchatsNom, int contactAchatsTel, String respoFournisseurNom, String respoFournisseurFonction) {
        this.id = id;
        this.raisonSociale = raisonSociale;
        this.formeJuridique = formeJuridique;
        this.siret = siret;
        this.numTva = numTva;
        this.rcs = rcs;
        this.adresse = adresse;
        this.telephone = telephone;
        this.capital = capital;
        this.villeInscription = villeInscription;
        this.representantNom = representantNom;
        this.representantFonction = representantFonction;
        this.respoClientTel = respoClientTel;
        this.contactAchatsNom = contactAchatsNom;
        this.contactAchatsTel = contactAchatsTel;
        this.respoFournisseurNom = respoFournisseurNom;
        this.respoFournisseurFonction = respoFournisseurFonction;
    }

    public Clients() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getNumTva() {
        return numTva;
    }

    public void setNumTva(String numTva) {
        this.numTva = numTva;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getVilleInscription() {
        return villeInscription;
    }

    public void setVilleInscription(String villeInscription) {
        this.villeInscription = villeInscription;
    }

    public String getRepresentantNom() {
        return representantNom;
    }

    public void setRepresentantNom(String representantNom) {
        this.representantNom = representantNom;
    }

    public String getRepresentantFonction() {
        return representantFonction;
    }

    public void setRepresentantFonction(String representantFonction) {
        this.representantFonction = representantFonction;
    }

    public int getRespoClientTel() {
        return respoClientTel;
    }

    public void setRespoClientTel(int respoClientTel) {
        this.respoClientTel = respoClientTel;
    }

    public String getContactAchatsNom() {
        return contactAchatsNom;
    }

    public void setContactAchatsNom(String contactAchatsNom) {
        this.contactAchatsNom = contactAchatsNom;
    }

    public int getContactAchatsTel() {
        return contactAchatsTel;
    }

    public void setContactAchatsTel(int contactAchatsTel) {
        this.contactAchatsTel = contactAchatsTel;
    }

    public String getRespoFournisseurNom() {
        return respoFournisseurNom;
    }

    public void setRespoFournisseurNom(String respoFournisseurNom) {
        this.respoFournisseurNom = respoFournisseurNom;
    }

    public String getRespoFournisseurFonction() {
        return respoFournisseurFonction;
    }

    public void setRespoFournisseurFonction(String respoFournisseurFonction) {
        this.respoFournisseurFonction = respoFournisseurFonction;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clients)) return false;

        Clients clients = (Clients) o;

        if (getId() != clients.getId()) return false;
        if (getTelephone() != clients.getTelephone()) return false;
        if (getCapital() != clients.getCapital()) return false;
        if (getRespoClientTel() != clients.getRespoClientTel()) return false;
        if (getContactAchatsTel() != clients.getContactAchatsTel()) return false;
        if (!getRaisonSociale().equals(clients.getRaisonSociale())) return false;
        if (!getFormeJuridique().equals(clients.getFormeJuridique())) return false;
        if (!getSiret().equals(clients.getSiret())) return false;
        if (!getNumTva().equals(clients.getNumTva())) return false;
        if (getRcs() != null ? !getRcs().equals(clients.getRcs()) : clients.getRcs() != null) return false;
        if (getAdresse() != null ? !getAdresse().equals(clients.getAdresse()) : clients.getAdresse() != null) return false;
        if (getVilleInscription() != null ? !getVilleInscription().equals(clients.getVilleInscription()) : clients.getVilleInscription() != null) return false;
        if (getRepresentantNom() != null ? !getRepresentantNom().equals(clients.getRepresentantNom()) : clients.getRepresentantNom() != null) return false;
        if (getRepresentantFonction() != null ? !getRepresentantFonction().equals(clients.getRepresentantFonction()) : clients.getRepresentantFonction() != null) return false;
        if (getContactAchatsNom() != null ? !getContactAchatsNom().equals(clients.getContactAchatsNom()) : clients.getContactAchatsNom() != null) return false;
        if (getRespoFournisseurNom() != null ? !getRespoFournisseurNom().equals(clients.getRespoFournisseurNom()) : clients.getRespoFournisseurNom() != null) return false;
        return getRespoFournisseurFonction() != null ? getRespoFournisseurFonction().equals(clients.getRespoFournisseurFonction()) : clients.getRespoFournisseurFonction() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getRaisonSociale().hashCode();
        result = 31 * result + getFormeJuridique().hashCode();
        result = 31 * result + getSiret().hashCode();
        result = 31 * result + getNumTva().hashCode();
        result = 31 * result + (getRcs() != null ? getRcs().hashCode() : 0);
        result = 31 * result + (getAdresse() != null ? getAdresse().hashCode() : 0);
        result = 31 * result + getTelephone();
        result = 31 * result + getCapital();
        result = 31 * result + (getVilleInscription() != null ? getVilleInscription().hashCode() : 0);
        result = 31 * result + (getRepresentantNom() != null ? getRepresentantNom().hashCode() : 0);
        result = 31 * result + (getRepresentantFonction() != null ? getRepresentantFonction().hashCode() : 0);
        result = 31 * result + getRespoClientTel();
        result = 31 * result + (getContactAchatsNom() != null ? getContactAchatsNom().hashCode() : 0);
        result = 31 * result + getContactAchatsTel();
        result = 31 * result + (getRespoFournisseurNom() != null ? getRespoFournisseurNom().hashCode() : 0);
        result = 31 * result + (getRespoFournisseurFonction() != null ? getRespoFournisseurFonction().hashCode() : 0);
        return result;
    }
}
