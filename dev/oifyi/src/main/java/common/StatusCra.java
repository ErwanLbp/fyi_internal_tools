package common;

/**
 * <h1>common StatusCra</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class StatusCra {

    private int id_status_cra;

    private String libelle;

    public StatusCra(int id_status_cra, String libelle) {
        this(libelle);
        this.id_status_cra = id_status_cra;
    }

    public StatusCra(String libelle) {
        this.libelle = libelle;
    }

    public int getId_status_cra() {
        return id_status_cra;
    }

    public void setId_status_cra(int id_status_cra) {
        this.id_status_cra = id_status_cra;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
