package common;

/**
 * Created by eisti on 03/11/16.
 */
public class Consultant_Role {
    private int id_consultant;

    private int id_role;

    public Consultant_Role(int id_consultant, int id_role) {
        this.id_consultant = id_consultant;
        this.id_role = id_role;
    }

    public int getId_consultant() {
        return id_consultant;
    }

    public void setId_consultant(int id_consultant) {
        this.id_consultant = id_consultant;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}
