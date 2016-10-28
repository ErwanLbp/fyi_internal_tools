/*
package dao;

import common.Adresse;
import common.Client;
import common.Consultant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

*/
/**
 * <h1>PACKAGE_NAME TestRoleDAO</h1>
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 25-10-2016
 *//*

public class TestConsultantDAO {

    private static Consultant consultant = new Client("a", "b", "c", "d");
    private static Consultant consultant_insert = new Client("a_insert", "b_insert", "c_insert", "d_insert", new Adresse(1, "e_insert", 2, "f_insert"));
    private static Consultant consultant_update = new Client("a_update", "b_update", "c_update", "d_update", new Adresse(1, "e_update", 2, "f_update"));

    @Before
    public void insertLigneBDD() {
        ClientDAO.insert(client);
        client.setId(ClientDAO.get(client.getRaison_sociale()).getId());
    }

    @After
    public void deleteLigneBDD() {
        ClientDAO.delete(client.getId());
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        ClientDAO.delete(client.getRaison_sociale());
        ClientDAO.delete(client_insert.getRaison_sociale());
        ClientDAO.delete(client_update.getRaison_sociale());
    }

    @Test
    public void testInsert() {
        assertTrue(ClientDAO.insert(client_insert));
        Client actual = ClientDAO.get(client_insert.getRaison_sociale());
        assert actual != null;
        client_insert.setId(actual.getId());
        assertEquals(client_insert.getRaison_sociale(), actual.getRaison_sociale());
    }

    @Test
    public void testUpdate() {
        client_update.setId(client.getId());
        assertTrue(ClientDAO.update(client_update));
        Client actual = ClientDAO.get(client_update.getRaison_sociale());
        assert actual != null;
        assertEquals(client_update.getRaison_sociale(), actual.getRaison_sociale());
    }

    @Test
    public void testDeleteById() {
        assertTrue(ClientDAO.delete(client.getId()));
        Client actual = ClientDAO.get(client.getId());
        assertNull(actual);
    }

    @Test
    public void testDeleteByRaisonSociale() {
        assertTrue(ClientDAO.delete(client.getRaison_sociale()));
        Client actual = ClientDAO.get(client.getId());
        assertNull(actual);
    }

    @Test
    public void testGetId() {
        Client actual = ClientDAO.get(client.getId());
        assert actual != null;
        assertEquals(client.getRaison_sociale(), actual.getRaison_sociale());
    }

    @Test
    public void testGetRaisonSociale() {
        Client actual = ClientDAO.get(client.getRaison_sociale());
        assert actual != null;
        assertEquals(client.getId(), actual.getId());
    }

    @Test
    public void testGetIdInexistant() {
        Client actual = ClientDAO.get(-1);
        assertNull(actual);
    }

    @Test
    public void testGetRaisonSocialeInexistant() {
        Client actual = ClientDAO.get("###");
        assertNull(actual);
    }

}
*/
