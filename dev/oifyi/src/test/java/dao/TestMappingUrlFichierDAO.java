package dao;

import common.MappingUrlFichierPK;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    public void insertLigneBDD(){
        MappingUrlFichierDAO.insertOrUpdate("test_page","test_mode","test_fichier");
    }

    @After
    public void deleteLigneBDD(){
        MappingUrlFichierDAO.delete(new MappingUrlFichierPK("test_page","test_mode"));
    }

    @Test
    public void testInsert(){
        MappingUrlFichierPK mufpk = new MappingUrlFichierPK("test_page","test_mode");
        String actual = MappingUrlFichierDAO.getFichier(mufpk);
        assertEquals("test_fichier",actual);
    }

    @Test
    public void testDelete(){
        MappingUrlFichierPK mufpk = new MappingUrlFichierPK("test_page","test_mode");
        MappingUrlFichierDAO.delete(mufpk);
        String actual = MappingUrlFichierDAO.getFichier(mufpk);
        assertNull(actual);
    }

    @Test
    public void testGetFichierExistant(){
        MappingUrlFichierPK mufpk = new MappingUrlFichierPK("test_page","test_mode");
        String actual = MappingUrlFichierDAO.getFichier(mufpk);
        assertEquals("test_fichier",actual);
    }

    @Test
    public void testGetFichierInexistant(){
        MappingUrlFichierPK mufpk = new MappingUrlFichierPK("test_page_fake","test_mode_fake");
        String actual = MappingUrlFichierDAO.getFichier(mufpk);
        assertNull(actual);
    }

}
