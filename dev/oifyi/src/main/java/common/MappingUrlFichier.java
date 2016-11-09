package common;

import java.io.Serializable;

/**
 * <h1>common Mapping_url_fichier</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */

public class MappingUrlFichier implements Serializable {

    private int id_muf;

    private String nomPage;

    private String nomMode;

    private String cheminFichier;

    /**
     * Constructeur MappingUrlFichier sans id
     * @param nomPage
     * @param nomMode
     * @param cheminFichier
     */
    public MappingUrlFichier(String nomPage, String nomMode, String cheminFichier) {
        this.nomPage = nomPage;
        this.nomMode = nomMode;
        this.cheminFichier = cheminFichier;
    }

    /**
     * Constructeur MappingUrlFichier avec id
     * @param id_muf
     * @param nomPage
     * @param nomMode
     * @param cheminFichier
     */
    public MappingUrlFichier(int id_muf, String nomPage, String nomMode, String cheminFichier) {
        this(nomPage, nomMode, cheminFichier);
        this.id_muf = id_muf;
    }

    /**
     * Getter id_muf
     * @return id_muf
     */
    public int getId_muf() {
        return id_muf;
    }

    /**
     * Setter id_muf
     * @param id_muf
     */
    public void setId_muf(int id_muf) {
        this.id_muf = id_muf;
    }

    /**
     * Getter nomPage
     * @return nomPage
     */
    public String getNomPage() {
        return nomPage;
    }

    /**
     * Setter nomPage
     * @param nomPage
     */
    public void setNomPage(String nomPage) {
        this.nomPage = nomPage;
    }

    /**
     * Getter nomMode
     * @return nomMode
     */
    public String getNomMode() {
        return nomMode;
    }

    /**
     * Setter nomMode
     * @param nomMode
     */
    public void setNomMode(String nomMode) {
        this.nomMode = nomMode;
    }

    /**
     * Getter cheminFichier
     * @return cheminFichier
     */
    public String getCheminFichier() {
        return cheminFichier;
    }

    /**
     * Setter cheminFichier
     * @param cheminFichier
     */
    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    /**
     * Retourne l'url avec le nom de la page et le nom du mode
     *
     */
    public String formerUrl() {
        return "/oifyi/index.jsp?page=" + this.getNomPage() + "&mode=" + this.getNomMode();
    }
}
