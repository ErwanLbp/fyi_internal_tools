package dao;

import common.*;
import org.junit.*;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * <h1>dao TestCraMoisDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class TestCraJourDAO {

    private static Client client = new Client("a", "b", "c", "d", new Adresse(1, "e", 2, "f"));
    private static Client client_insert = new Client("a_insert", "b_insert", "c_insert", "d_insert", new Adresse(1, "e_insert", 2, "f_insert"));

    private static Mission mission;
    private static Mission mission_insert;

    private static Role role = new Role("a");
    private static Role role_insert = new Role("a_insert");

    private static Consultant consultant;
    private static Consultant consultant_insert;

    private static StatusCra statusCra = new StatusCra("a");
    private static StatusCra statusCra_insert = new StatusCra("a_insert");

    private static CraMois craMois;
    private static CraMois craMois_insert;

    private static CraJour craJour;
    private static CraJour craJour_insert;
    private static CraJour craJour_update;


    @BeforeClass
    public static void insertsAvantClasse() {

        ClientDAO.insert(client);
        client.setId(ClientDAO.get(client.getRaison_sociale()).getId());
        ClientDAO.insert(client_insert);
        client_insert.setId(ClientDAO.get(client_insert.getRaison_sociale()).getId());

        mission = new Mission("a", "b", client.getId(), Date.valueOf("2000-01-01"), Date.valueOf("2000-01-31"), 1);
        MissionDAO.insert(mission);
        mission.setId_mission(MissionDAO.get(mission.getNom()).getId_mission());
        mission_insert = new Mission("a_insert", "b_insert", client_insert.getId(), Date.valueOf("2001-01-01"), Date.valueOf("2001-01-31"), 1);
        MissionDAO.insert(mission_insert);
        mission_insert.setId_mission(MissionDAO.get(mission_insert.getNom()).getId_mission());

        RoleDAO.insert(role);
        role.setId_role(RoleDAO.get(role.getLibelle()).getId_role());
        RoleDAO.insert(role_insert);
        role_insert.setId_role(RoleDAO.get(role_insert.getLibelle()).getId_role());

        consultant = new Consultant("a", "b", "c", "d", role.getId_role());
        ConsultantDAO.insert(consultant);
        consultant.setId(ConsultantDAO.get(consultant.getUsername()).getId());
        consultant_insert = new Consultant("a_insert", "b_insert", "c_insert", "d_insert", role_insert.getId_role());
        ConsultantDAO.insert(consultant_insert);
        consultant_insert.setId(ConsultantDAO.get(consultant_insert.getUsername()).getId());

        StatusCraDAO.insert(statusCra);
        statusCra.setId_status_cra(StatusCraDAO.get(statusCra.getLibelle()).getId_status_cra());
        StatusCraDAO.insert(statusCra_insert);
        statusCra_insert.setId_status_cra(StatusCraDAO.get(statusCra_insert.getLibelle()).getId_status_cra());

        craMois = new CraMois(mission.getId_mission(), consultant.getId(), Date.valueOf("2001-01-01"), statusCra.getId_status_cra());
        CraMoisDAO.insert(craMois);
        craMois.setId_cra_mois(CraMoisDAO.get(craMois.getMission_id(), craMois.getConsultant_id(), craMois.getMois_annee().toString()).getId_cra_mois());
        craMois_insert = new CraMois(mission_insert.getId_mission(), consultant_insert.getId(), Date.valueOf("2001-01-01"), statusCra_insert.getId_status_cra());
        CraMoisDAO.insert(craMois_insert);
        craMois_insert.setId_cra_mois(CraMoisDAO.get(craMois_insert.getMission_id(), craMois_insert.getConsultant_id(), craMois_insert.getMois_annee().toString()).getId_cra_mois());
    }

    @AfterClass
    public static void deletesApresClasse() {
        CraJourDAO.delete(craJour_insert.getId_cramois(), craJour_insert.getJour());
        CraJourDAO.delete(craJour_update.getId_cramois(), craJour_update.getJour());
        CraMoisDAO.delete(craMois_insert.getId_cra_mois());
        CraMoisDAO.delete(craMois.getId_cra_mois());
        MissionDAO.delete(mission.getNom());
        MissionDAO.delete(mission_insert.getNom());
        ConsultantDAO.delete(consultant.getId());
        ConsultantDAO.delete(consultant_insert.getId());
        ClientDAO.delete(client.getRaison_sociale());
        ClientDAO.delete(client_insert.getRaison_sociale());
        RoleDAO.delete(role.getLibelle());
        RoleDAO.delete(role_insert.getLibelle());
        StatusCraDAO.delete(statusCra.getLibelle());
        StatusCraDAO.delete(statusCra_insert.getLibelle());
    }

    @Before
    public void insertLigneBDD() {
        craJour = new CraJour(CraMoisDAO.get(craMois.getMission_id(), craMois.getConsultant_id(), craMois.getMois_annee().toString()).getId_cra_mois(), 1, 0.5);
        CraJourDAO.insert(craJour);
    }

    @After
    public void deleteLigneBDD() {
        CraJourDAO.delete(craJour.getId_cramois(), craJour.getJour());
    }

    @Test
    public void testInsert() {
        craJour_insert = new CraJour(CraMoisDAO.get(craMois_insert.getMission_id(), craMois_insert.getConsultant_id(), craMois_insert.getMois_annee().toString()).getId_cra_mois(), 1, 2);
        assertTrue(CraJourDAO.insert(craJour_insert));
        CraJour actual = CraJourDAO.get(craJour_insert.getId_cramois(), craJour_insert.getJour());
        assert actual != null;
        assertEquals(craJour_insert.getId_cramois(), actual.getId_cramois());
        assertEquals(craJour_insert.getJour(), actual.getJour());
    }

    @Test
    public void testUpdate() {
        craJour_update = new CraJour(CraJourDAO.get(craJour.getId_cramois(), craJour.getJour()).getId_cramois(), CraJourDAO.get(craJour.getId_cramois(), craJour.getJour()).getJour(), 3);
        assertTrue(CraJourDAO.update(craJour_update));
        CraJour actual = CraJourDAO.get(craJour_update.getId_cramois(), craJour_update.getJour());
        assert actual != null;
        assertEquals(craJour_update.getId_cramois(), actual.getId_cramois());
        assertEquals(craJour_update.getJour(), actual.getJour());
    }

    @Test
    public void testDeleteById() {
        assertTrue(CraJourDAO.delete(craJour.getId_cramois(), craJour.getJour()));
        assertNull(CraJourDAO.get(craJour.getId_cramois(), craJour.getJour()));
    }


    @Test
    public void testGetIdDateInexistant() {
        CraJour actual = CraJourDAO.get(-1, 1);
        assertNull(actual);
    }
}
