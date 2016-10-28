package dao;

import common.Parametrage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by eisti on 28/10/16.
 */
public class TestParametrageDAO {
    private static Parametrage parametrage = new Parametrage("libelle","valeur");
    private static Parametrage parametrage_insert;
    private static Parametrage parametrage_update;

    @AfterClass
    public static void deletesApresClasse() {
        ParametrageDAO.delete(parametrage.getLibelle());
        ParametrageDAO.delete(parametrage_insert.getLibelle());
        ParametrageDAO.delete(parametrage_update.getLibelle());
    }

    @Before
    public void insertLigneBDD() {
        ParametrageDAO.insert(parametrage);
    }

    @After
    public void deleteLigneBDD() {
        ParametrageDAO.delete(parametrage.getLibelle());
    }

    @Test
    public void testInsert() {
        parametrage_insert = new Parametrage("libelle_insert", "valeur_insert");
        assertTrue(ParametrageDAO.insert(parametrage_insert));
        Parametrage actual = ParametrageDAO.get(parametrage_insert.getLibelle());
        assert  actual != null;
        parametrage_insert.setLibelle(actual.getLibelle());
        assertEquals(parametrage_insert.getValeur(),actual.getValeur());
    }

    @Test
    public void testUpdate() {
        parametrage_update = new Parametrage(ParametrageDAO.get(parametrage.getLibelle()).getLibelle(),"valeur_update");
        assertTrue(ParametrageDAO.update(parametrage_update));
        Parametrage actual = ParametrageDAO.get(parametrage_update.getLibelle());
        assert actual != null;
        assertEquals(parametrage_update.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testDelete() {
        assertTrue(ParametrageDAO.delete(parametrage.getLibelle()));
        Parametrage actual = ParametrageDAO.get(parametrage.getLibelle());
        assertNull(actual);
    }

    @Test
    public void testGet() {
        Parametrage actual = ParametrageDAO.get(parametrage.getLibelle());
        assert actual != null;
        assertEquals(parametrage.getLibelle(), actual.getLibelle());
    }

    @Test
    public void testGetIdInexistant() {
        Parametrage actual = ParametrageDAO.get("lol");
        assertNull(actual);
    }

}
