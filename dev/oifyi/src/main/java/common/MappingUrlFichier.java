package common;

import javax.persistence.*;

/**
 * <h1>common Mapping_url_fichier</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */

@Entity
@Table(name = "MAPPING_URL_FICHIER")
public class MappingUrlFichier {

    @EmbeddedId
    private MappingUrlFichierPK mufpk;

    @Column(name = "CHEMIN_FICHIER", nullable = false)
    private String cheminFichier;

    public MappingUrlFichier(String nomPage, String nomMode, String cheminFichier) {
        mufpk = new MappingUrlFichierPK(nomPage, nomMode);
        this.cheminFichier = cheminFichier;
    }

    public MappingUrlFichier(MappingUrlFichierPK mufpk) {
        this(mufpk.getNomPage(), mufpk.getNomMode(), "");
    }

    public MappingUrlFichier() {
        this("", "", "");
    }

    public MappingUrlFichierPK getMufpk() {
        return mufpk;
    }

    public void setMufpk(MappingUrlFichierPK mufpk) {
        this.mufpk = mufpk;
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

        if (!getMufpk().equals(that.getMufpk())) return false;
        return getCheminFichier().equals(that.getCheminFichier());

    }

    @Override
    public int hashCode() {
        int result = getMufpk().hashCode();
        result = 31 * result + getCheminFichier().hashCode();
        return result;
    }
}
