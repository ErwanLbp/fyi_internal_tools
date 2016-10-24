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

    public MappingUrlFichier(String nomPage, String nomMode, String cheminFichier) {
        this.nomPage = nomPage;
        this.nomMode = nomMode;
        this.cheminFichier = cheminFichier;
    }

    public MappingUrlFichier(int id_muf, String nomPage, String nomMode, String cheminFichier) {
        this(nomPage, nomMode, cheminFichier);
        this.id_muf = id_muf;
    }

    public int getId_muf() {
        return id_muf;
    }

    public void setId_muf(int id_muf) {
        this.id_muf = id_muf;
    }

    public String getNomPage() {
        return nomPage;
    }

    public void setNomPage(String nomPage) {
        this.nomPage = nomPage;
    }

    public String getNomMode() {
        return nomMode;
    }

    public void setNomMode(String nomMode) {
        this.nomMode = nomMode;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MappingUrlFichier)) return false;

        MappingUrlFichier that = (MappingUrlFichier) o;

        if (getId_muf() != that.getId_muf()) return false;
        if (!getNomPage().equals(that.getNomPage())) return false;
        if (!getNomMode().equals(that.getNomMode())) return false;
        return getCheminFichier().equals(that.getCheminFichier());

    }

    @Override
    public int hashCode() {
        int result = getId_muf();
        result = 31 * result + getNomPage().hashCode();
        result = 31 * result + getNomMode().hashCode();
        result = 31 * result + getCheminFichier().hashCode();
        return result;
    }

}
