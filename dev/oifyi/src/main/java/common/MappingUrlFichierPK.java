package common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <h1>common MappingUrlFichierPK</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */
@Embeddable
public class MappingUrlFichierPK implements Serializable {

    @Column(name = "NOM_PAGE", nullable = false)
    private String nomPage;

    @Column(name = "NOM_MODE", nullable = false)
    private String nomMode;

    public MappingUrlFichierPK(String nomPage, String nomMode) {
        this.nomPage = nomPage;
        this.nomMode = nomMode;
    }

    public MappingUrlFichierPK() {
        this("", "");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MappingUrlFichierPK that = (MappingUrlFichierPK) o;

        if (nomPage != null ? !nomPage.equals(that.nomPage) : that.nomPage != null) return false;
        if (nomMode != null ? !nomMode.equals(that.nomMode) : that.nomMode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nomPage != null ? nomPage.hashCode() : 0;
        result = 31 * result + (nomMode != null ? nomMode.hashCode() : 0);
        return result;
    }
}
