package dao;

import common.*;
import org.flywaydb.core.Flyway;
import org.junit.*;
import dao.MissionDAO;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * <h1>dao TestCraMoisDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class TestCraMoisDAO {

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
    private static CraMois craMois_update;

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
    }

    @AfterClass
    public static void deletesApresClasse() {
        CraMoisDAO.delete(craMois_insert.getId_cra_mois());
        CraMoisDAO.delete(craMois_update.getId_cra_mois());
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
        craMois = new CraMois(mission.getId_mission(), consultant.getId(), Date.valueOf("2001-01-01"), statusCra.getId_status_cra());
        CraMoisDAO.insert(craMois);
        craMois.setId_cra_mois(CraMoisDAO.get(mission.getId_mission(), consultant.getId(), "2001-01-01").getId_cra_mois());
    }

    @After
    public void deleteLigneBDD() {
        CraMoisDAO.delete(craMois.getId_cra_mois());
    }

    @Test
    public void testInsert() {
        craMois_insert = new CraMois(mission_insert.getId_mission(), consultant_insert.getId(), Date.valueOf("2002-01-01"), statusCra_insert.getId_status_cra());
        assertTrue(CraMoisDAO.insert(craMois_insert));
        CraMois actual = CraMoisDAO.get(craMois_insert.getMission_id(), craMois_insert.getConsultant_id(), craMois_insert.getMois_annee().toString());
        assert actual != null;
        craMois_insert.setId_cra_mois(actual.getId_cra_mois());
        assertEquals(craMois_insert.getMois_annee(), actual.getMois_annee());
    }

    @Test
    public void testUpdate() {
        craMois_update = new CraMois(craMois.getId_cra_mois(), mission.getId_mission(), consultant.getId(), Date.valueOf("2003-01-01"), statusCra.getId_status_cra());
        assertTrue(CraMoisDAO.update(craMois_update));
        CraMois actual = CraMoisDAO.get(craMois_update.getMission_id(), craMois_update.getConsultant_id(), craMois_update.getMois_annee().toString());
        assert actual != null;
        assertEquals(craMois_update.getMois_annee(), actual.getMois_annee());
    }

    @Test
    public void testDeleteById() {
        assertTrue(CraMoisDAO.delete(craMois.getId_cra_mois()));
        assertNull(CraMoisDAO.get(craMois.getId_cra_mois()));
    }

    @Test
    public void testGetId() {
        CraMois actual = CraMoisDAO.get(craMois.getId_cra_mois());
        assert actual != null;
        assertEquals(craMois.getId_cra_mois(), actual.getId_cra_mois());
    }

    @Test
    public void testGetIdInexistant() {
        CraMois actual = CraMoisDAO.get(-1);
        assertNull(actual);
    }
}
