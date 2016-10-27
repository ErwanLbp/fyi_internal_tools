package dao;

import common.StatusCra;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * TODO Description
 *
 * @author Croute
 * @version 1.0
 * @since 26-10-2016
 */

public class TestStatusCraDAO {

    private static StatusCra status_cra = new StatusCra("test_status_cra");
    private static StatusCra status_cra_insert = new StatusCra("test_status_cra_insert");
    private static StatusCra status_cra_update = new StatusCra("test_status_cra_update");

    @Before
    public void insertLigneBDD() {
        StatusCraDAO.insert(status_cra);
        status_cra.setId_status_cra(StatusCraDAO.get(status_cra.getLibelle()).getId_status_cra());
    }

    @After
    public void deleteLigneBDD() {
        StatusCraDAO.delete(status_cra.getLibelle());
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        StatusCraDAO.delete(status_cra_update.getLibelle());
        StatusCraDAO.delete(status_cra_insert.getLibelle());
    }

    @Test
    public void testInsert() {
        assertTrue(StatusCraDAO.insert(status_cra_insert));
        StatusCra actual = StatusCraDAO.get(status_cra_insert.getLibelle());
        assert actual != null;
        assertEquals(status_cra_insert.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testUpdate() {
        status_cra_update.setId_status_cra(status_cra.getId_status_cra());
        assertTrue(StatusCraDAO.update(status_cra_update));
        StatusCra actual = StatusCraDAO.get(status_cra_update.getLibelle());
        assert actual != null;
        assertEquals(status_cra_update.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testDelete() {
        assertTrue(StatusCraDAO.delete(status_cra.getLibelle()));
        assertNull(StatusCraDAO.get(status_cra.getLibelle()));
    }

    @Test
    public void testGetExistantLibelle() {
        StatusCra actual = StatusCraDAO.get(status_cra.getLibelle());
        assert actual != null;
        assertEquals(status_cra.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testGetInexistant() {
        StatusCra actual = StatusCraDAO.get("test_status_cra_fake");
        assertNull(actual);
    }

}