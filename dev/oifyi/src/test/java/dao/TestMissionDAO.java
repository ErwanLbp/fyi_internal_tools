package dao;

import common.Client;
import common.Mission;
import org.junit.*;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * <h1>dao TestMissionDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 25-10-2016
 */
public class TestMissionDAO {

    private static Client client = new Client("a", "b", "c", "d", 1, "e", 2, "f");
    private static Client client_insert = new Client("a_insert", "b_insert", "c_insert", "d_insert", 1, "e_insert", 2, "f_insert");

    private static Mission mission;
    private static Mission mission_insert;
    private static Mission mission_update;

    @BeforeClass
    public static void insertsAvantClasse() {
        ClientDAO.insert(client);
        client.setId(ClientDAO.get(client.getRaison_sociale()).getId());
        ClientDAO.insert(client_insert);
        client_insert.setId(ClientDAO.get(client_insert.getRaison_sociale()).getId());
    }

    @Before
    public void insertLigneBDD() {
        mission = new Mission("a", "b", client.getId(), Date.valueOf("2000-01-01"), Date.valueOf("2000-01-31"), 1);
        MissionDAO.insert(mission);
        mission.setId_mission(MissionDAO.get(mission.getNom()).getId_mission());
    }

    @After
    public void deleteLigneBDD() {
        MissionDAO.delete(mission.getId_mission());
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        MissionDAO.delete(mission_insert.getNom());
        MissionDAO.delete(mission_update.getNom());
        ClientDAO.delete(client.getRaison_sociale());
        ClientDAO.delete(client_insert.getRaison_sociale());
    }

    @Test
    public void testInsert() {
        mission_insert = new Mission("a_insert", "b_insert", client_insert.getId(), Date.valueOf("2001-01-01"), Date.valueOf("2001-01-31"), 1);
        assertTrue(MissionDAO.insert(mission_insert));
        Mission actual = MissionDAO.get(mission_insert.getNom());
        assert actual != null;
        mission_insert.setId_mission(actual.getId_mission());
        assertEquals(mission_insert.getNom(), actual.getNom());
    }

    @Test
    public void testUpdate() {
        mission_update = new Mission(mission.getId_mission(), "a_update", "b_update", client.getId(), Date.valueOf("2002-01-01"), Date.valueOf("2002-01-31"), 1);
        assertTrue(MissionDAO.update(mission_update));
        Mission actual = MissionDAO.get(mission_update.getNom());
        assert actual != null;
        assertEquals(mission_update.getNom(), actual.getNom());
    }

    @Test
    public void testDeleteById() {
        assertTrue(MissionDAO.delete(mission.getId_mission()));
        assertNull(MissionDAO.get(mission.getId_mission()));
    }

    @Test
    public void testDeleteByNom() {
        assertTrue(MissionDAO.delete(mission.getNom()));
        assertNull(MissionDAO.get(mission.getNom()));
    }

    @Test
    public void testGetId() {
        Mission actual = MissionDAO.get(mission.getId_mission());
        assert actual != null;
        assertEquals(mission.getNom(), actual.getNom());
    }

    @Test
    public void testGetNom() {
        Mission actual = MissionDAO.get(mission.getNom());
        assert actual != null;
        assertEquals(mission.getId_mission(), actual.getId_mission());
    }

    @Test
    public void testGetIdInexistant() {
        Mission actual = MissionDAO.get(-1);
        assertNull(actual);
    }

    @Test
    public void testGetNomInexistant() {
        Mission actual = MissionDAO.get("###");
        assertNull(actual);
    }

}
