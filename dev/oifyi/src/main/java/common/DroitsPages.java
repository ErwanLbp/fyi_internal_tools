package common;

/**
 * <h1>common DroitsPages</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 24-10-2016
 */
public class DroitsPages {


    private int id_page;

    private int id_role;

    public DroitsPages(int id_page, int id_role) {
        this.id_page = id_page;
        this.id_role = id_role;
    }

    public int getId_page() {
        return id_page;
    }

    public void setId_page(int id_page) {
        this.id_page = id_page;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}
