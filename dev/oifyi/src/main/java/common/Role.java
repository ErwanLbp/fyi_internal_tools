package common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h1>common Mapping_url_fichier</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    private long id;

    @Column(name = "LIBELLE", nullable = false)
    private String libelle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (libelle != null ? !libelle.equals(role.libelle) : role.libelle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        return result;
    }
}
