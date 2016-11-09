package common;

/**
 * Created by eisti on 03/11/16.
 */
public class Consultant_Role {
    private int id_consultant;

    private int id_role;

    /**
     * Constructeur Consultant_Role
     * @param id_consultant
     * @param id_role
     */
    public Consultant_Role(int id_consultant, int id_role) {
        this.id_consultant = id_consultant;
        this.id_role = id_role;
    }

    /**
     * Getter id_consultant
     * @return id_consultant
     */
    public int getId_consultant() {
        return id_consultant;
    }

    /**
     * Setter id_consultant
     * @param id_consultant
     */
    public void setId_consultant(int id_consultant) {
        this.id_consultant = id_consultant;
    }

    /**
     * Getter id_role
     * @return id_role
     */
    public int getId_role() {
        return id_role;
    }

    /**
     * Setter id_role
     * @param id_role
     */
    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}
