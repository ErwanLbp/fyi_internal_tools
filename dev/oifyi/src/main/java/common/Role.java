package common;

/**
 * <h1>common Mapping_url_fichier</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */

public class Role {

    private int id_role;

    private String libelle;

    public Role(String libelle) {
        this.libelle = libelle;
    }

    public Role(int id_role, String libelle) {
        this(libelle);
        this.id_role = id_role;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
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
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return getId_role() == role.getId_role() && getLibelle().equals(role.getLibelle());
    }

    @Override
    public int hashCode() {
        int result = getId_role();
        result = 31 * result + getLibelle().hashCode();
        return result;
    }
}

