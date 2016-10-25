package dao;

import common.Client;
import common.Role;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <h1>PACKAGE_NAME TestRoleDAO</h1>
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 25-10-2016
 */
public class TestClientDAO {

    @Before
    public void insertLigneBDD() {
        ClientDAO.insert(new Client("test_client","test_client"));
    }

    @After
    public void deleteLigneBDD() { ClientDAO.deleteByNomPrenom("test_client","test_client");}

    @AfterClass
    public static void suppressionsInsertTests() {
        RoleDAO.deleteByLibelle("test_role_update");
        RoleDAO.deleteByLibelle("test_role_insert");
    }

    @Test
    public void testContrainteUnicite() {assertFalse(ClientDAO.insert(new Client("test_client","test_client")));}

    @Test
    public void testInsert() {
        assertTrue(ClientDAO.insert(new Client("test_client_insert","test_client_insert")));
        Client actual = ClientDAO.getByNomPrenom("test_client_insert","test_client_insert");
        assert actual != null;
        assertEquals("test_client_insert", actual.getNom());
        assertEquals("test_client_insert", actual.getPrenom());
    }

    @Test
    public void testUpdate() {
        Client muf = ClientDAO.getByNomPrenom("test_client","test_client");
        assert muf != null;
        assertTrue(ClientDAO.update(new Client(muf.getId(),"test_client_update","test_client_update")));
        Client actual = ClientDAO.getByNomPrenom("test_client_update","test_client_update");
        assert actual != null;
        assertEquals("test_client_update", actual.getNom());
        assertEquals("test_client_update", actual.getPrenom());
    }

    @Test
    public void testDelete() {
        assertTrue(ClientDAO.deleteByNomPrenom("test_client","test_client"));
        Client actual = ClientDAO.getByNomPrenom("test_client","test_client");
        assertNull(actual);
    }

    @Test
    public void testGetExistantLibelle() {
        Client actual = ClientDAO.getByNomPrenom("test_client","test_client");
        assert actual != null;
        assertEquals("test_client", actual.getNom());
        assertEquals("test_client", actual.getPrenom());
    }

    @Test
    public void testGetInexistant() {
        Client actual = ClientDAO.getByNomPrenom("test_client_fake","test_client_fake");
        assertNull(actual);
    }

}
