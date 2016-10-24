package common;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

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
    @GeneratedValue//(strategy = GenerationType.TABLE, generator = "role_generator")
//    @TableGenerator(name = "role_generator", table = "ID_GEN", pkColumnName = "id_role", valueColumnName = "id_role_value", allocationSize = 1)
    @Column(name = "id_role", nullable = false)
    private int id_role;

    @Column(name = "LIBELLE", nullable = false)
    private String libelle;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "roles_autorises", targetEntity = MappingUrlFichier.class)
    private List<MappingUrlFichier> pages_autorises;

    public Role(String libelle) {
        this.libelle = libelle;
        this.pages_autorises = new LinkedList<>();
    }

    public Role() {
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

        if (getId_role() != role.getId_role()) return false;
        return getLibelle().equals(role.getLibelle());

    }

    @Override
    public int hashCode() {
        int result = getId_role();
        result = 31 * result + getLibelle().hashCode();
        return result;
    }

    public List<MappingUrlFichier> getPages_autorises() {
        return pages_autorises;
    }

    public void setPages_autorises(List<MappingUrlFichier> pages_autorises) {
        this.pages_autorises = pages_autorises;
    }
}

