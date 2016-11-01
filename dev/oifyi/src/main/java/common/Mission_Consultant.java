package common;

/**
 * Created by eisti on 31/10/16.
 */
public class Mission_Consultant {
    private int consultant_id;

    private int mission_id;

    private float prix;

    public Mission_Consultant(int consultant_id, int mission_id, float prix) {
        this.consultant_id = consultant_id;
        this.mission_id = mission_id;
        this.prix = prix;
    }

    public int getConsultant_id() {
        return consultant_id;
    }

    public void setConsultant_id(int consultant_id) {
        this.consultant_id = consultant_id;
    }

    public int getMission_id() {
        return mission_id;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
