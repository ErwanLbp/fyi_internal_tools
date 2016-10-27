package dao;

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
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */
public class TestRoleDAO {

    private static Role role = new Role("test_role");
    private static Role role_insert = new Role("test_role_insert");
    private static Role role_update = new Role("test_role_update");

    @Before
    public void insertLigneBDD() {
        RoleDAO.insert(role);
        role.setId_role(RoleDAO.get(role.getLibelle()).getId_role());
    }

    @After
    public void deleteLigneBDD() {
        RoleDAO.delete(role.getLibelle());
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        RoleDAO.delete(role_update.getLibelle());
        RoleDAO.delete(role_insert.getLibelle());
    }

    @Test
    public void testContrainteUnicite() {
        assertFalse(RoleDAO.insert(role));
    }

    @Test
    public void testInsert() {
        assertTrue(RoleDAO.insert(role_insert));
        Role actual = RoleDAO.get(role_insert.getLibelle());
        assert actual != null;
        role_insert.setId_role(actual.getId_role());
        assertEquals(role_insert.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testUpdate() {
        role_update.setId_role(role.getId_role());
        assertTrue(RoleDAO.update(role_update));
        Role actual = RoleDAO.get(role_update.getLibelle());
        assert actual != null;
        assertEquals(role_update.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testDelete() {
        assertTrue(RoleDAO.delete(role.getLibelle()));
        assertNull(RoleDAO.get(role.getLibelle()));
    }

    @Test
    public void testGetExistantLibelle() {
        Role actual = RoleDAO.get(role.getLibelle());
        assert actual != null;
        assertEquals(role.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testGetInexistant() {
        Role actual = RoleDAO.get("test_role_fake");
        assertNull(actual);
    }

    @Test
    public void testIsInDBExistant() {
        assertTrue(RoleDAO.isInDB(role.getId_role()));
    }

    @Test
    public void testIsInDBInExistant() {
        assertFalse(RoleDAO.isInDB(-1));
    }
}
