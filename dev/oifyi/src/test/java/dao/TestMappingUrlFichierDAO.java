package dao;

import common.MappingUrlFichier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <h1>PACKAGE_NAME TestMappingUrlFichierDao</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */
public class TestMappingUrlFichierDAO {

    @Before
    public void insertLigneBDD() {
        MappingUrlFichierDAO.insert(new MappingUrlFichier("test_page", "test_mode", "test_fichier"));
    }

    @After
    public void deleteLigneBDD() {
        MappingUrlFichierDAO.deleteByNomPageNomMode("test_page", "test_mode");
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        MappingUrlFichierDAO.deleteByNomPageNomMode("test_page_update", "test_mode_update");
        MappingUrlFichierDAO.deleteByNomPageNomMode("test_page_insert", "test_mode_insert");
    }

    @Test
    public void testContrainteUnicite() {
        assertFalse(MappingUrlFichierDAO.insert(new MappingUrlFichier("test_page", "test_mode", "test_fichier")));
    }

    @Test
    public void testInsert() {
        assertTrue(MappingUrlFichierDAO.insert(new MappingUrlFichier("test_page_insert", "test_mode_insert", "test_fichier_insert")));
        MappingUrlFichier actual = MappingUrlFichierDAO.getMuf("test_page_insert", "test_mode_insert");
        assert actual != null;
        assertEquals("test_fichier_insert", actual.getCheminFichier());
    }

    @Test
    public void testUpdate() {
        MappingUrlFichier muf = MappingUrlFichierDAO.getMuf("test_page", "test_mode");
        assert muf != null;
        assertTrue(MappingUrlFichierDAO.update(new MappingUrlFichier(muf.getId_muf(), "test_page_update", "test_mode_update", "test_fichier_update")));
        MappingUrlFichier actual = MappingUrlFichierDAO.getMuf("test_page_update", "test_mode_update");
        assert actual != null;
        assertEquals("test_fichier_update", actual.getCheminFichier());
    }

    @Test
    public void testDelete() {
        assertTrue(MappingUrlFichierDAO.deleteByNomPageNomMode("test_page", "test_mode"));
        MappingUrlFichier actual = MappingUrlFichierDAO.getMuf("test_page", "test_mode");
        assertNull(actual);
    }

    @Test
    public void testGetFichierExistant() {
        MappingUrlFichier actual = MappingUrlFichierDAO.getMuf("test_page", "test_mode");
        assert actual != null;
        assertEquals("test_fichier", actual.getCheminFichier());
    }

    @Test
    public void testGetFichierInexistant() {
        MappingUrlFichier actual = MappingUrlFichierDAO.getMuf("test_page_fake", "test_mode_fake");
        assertNull(actual);
    }

}
