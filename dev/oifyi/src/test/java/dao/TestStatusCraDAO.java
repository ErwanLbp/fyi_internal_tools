/*
package dao;

import common.StatusCra;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

*/
/**
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 26-10-2016
 *//*

public class TestStatusCraDAO {

    @Before
    public void insertLigneBDD() {
        StatusCraDAO.insert(new StatusCra("test_role"));
    }

    @After
    public void deleteLigneBDD() {
        RoleDAO.delete("test_role");
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        RoleDAO.delete("test_role_update");
        RoleDAO.delete("test_role_insert");
    }

    @Test
    public void testContrainteUnicite() {
        assertFalse(RoleDAO.insert(new Role("test_role")));
    }

    @Test
    public void testInsert() {
        assertTrue(RoleDAO.insert(new Role("test_role_insert")));
        Role actual = RoleDAO.get("test_role_insert");
        assert actual != null;
        assertEquals("test_role_insert", actual.getLibelle());
    }

    @Test
    public void testUpdate() {
        Role muf = RoleDAO.get("test_role");
        assert muf != null;
        assertTrue(RoleDAO.update(new Role(muf.getId_role(), "test_role_update")));
        Role actual = RoleDAO.get("test_role_update");
        assert actual != null;
        assertEquals("test_role_update", actual.getLibelle());
    }

    @Test
    public void testDelete() {
        assertTrue(RoleDAO.delete("test_role"));
        Role actual = RoleDAO.get("test_role");
        assertNull(actual);
    }

    @Test
    public void testGetExistantLibelle() {
        Role actual = RoleDAO.get("test_role");
        assert actual != null;
        assertEquals("test_role", actual.getLibelle());
    }

    @Test
    public void testGetInexistant() {
        Role actual = RoleDAO.get("test_role_fake");
        assertNull(actual);
    }

}
*/
