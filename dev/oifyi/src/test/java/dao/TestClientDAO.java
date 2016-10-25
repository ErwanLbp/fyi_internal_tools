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
        ClientDAO.insert(new Client("test_client_raison_sociale", "test_client_forme_juridique", "test_client_siret", "test_client_num_tva", "test_client_rcs", 1, "test_client_adresse_rue", 95000, "test_client_adresse_ville", "test_client_telephone", 1000000 , "test_client_ville_inscription", "test_client_representant_nom", "test_client_representant_fonction", "test_client_respo_client_tel", "test_client_contact_achats_nom", "test_client_contact_achats_tel", "test_client_respo_fournisseur_nom"));
    }

    @After
    public void deleteLigneBDD() { ClientDAO.deleteByFormeJuridique("test_client_forme_juridique");}

    @AfterClass
    public static void suppressionsInsertTests() {
        ClientDAO.deleteByFormeJuridique("test_client_update_forme_juridique");
        ClientDAO.deleteByFormeJuridique("test_client_insert_forme_juridique");
    }

    @Test
    public void testContrainteUnicite() {assertFalse(ClientDAO.insert(new Client("test_client_raison_sociale", "test_client_forme_juridique", "test_client_siret", "test_client_num_tva", "test_client_rcs", 1, "test_client_adresse_rue", 95000, "test_client_adresse_ville", "test_client_telephone", 1000000 , "test_client_ville_inscription", "test_client_representant_nom", "test_client_representant_fonction", "test_client_respo_client_tel", "test_client_contact_achats_nom", "test_client_contact_achats_tel", "test_client_respo_fournisseur_nom")));}

    @Test
    public void testInsert() {
        assertTrue(ClientDAO.insert(new Client("test_client_raison_sociale", "test_client_forme_juridique", "test_client_siret", "test_client_num_tva", "test_client_rcs", 1, "test_client_adresse_rue", 95000, "test_client_adresse_ville", "test_client_telephone", 1000000 , "test_client_ville_inscription", "test_client_representant_nom", "test_client_representant_fonction", "test_client_respo_client_tel", "test_client_contact_achats_nom", "test_client_contact_achats_tel", "test_client_respo_fournisseur_nom")));
        Client actual = ClientDAO.getByFormeJuridique("test_client_insert_forme_juridique");
        assert actual != null;
        assertEquals("test_client_insert_forme_juridique", actual.getForme_juridique());
    }

    @Test
    public void testUpdate() {
        Client muf = ClientDAO.getByFormeJuridique("test_client_forme_juridique");
        assert muf != null;
        assertTrue(ClientDAO.update(new Client(muf.getId(),"test_client_raison_sociale", "test_client_forme_juridique", "test_client_siret", "test_client_num_tva", "test_client_rcs", 1, "test_client_adresse_rue", 95000, "test_client_adresse_ville", "test_client_telephone", 1000000 , "test_client_ville_inscription", "test_client_representant_nom", "test_client_representant_fonction", "test_client_respo_client_tel", "test_client_contact_achats_nom", "test_client_contact_achats_tel", "test_client_respo_fournisseur_nom")));
        Client actual = ClientDAO.getByFormeJuridique("test_client_update_forme_juridique");
        assert actual != null;
        assertEquals("test_client_update_forme_juridique", actual.getForme_juridique());
    }

    @Test
    public void testDelete() {
        assertTrue(ClientDAO.deleteByFormeJuridique("test_client_forme_juridique"));
        Client actual = ClientDAO.getByFormeJuridique("test_client_forme_juridique");
        assertNull(actual);
    }

    @Test
    public void testGetExistantLibelle() {
        Client actual = ClientDAO.getByFormeJuridique("test_client_forme_juridique");
        assert actual != null;
        assertEquals("test_client_FJ", actual.getForme_juridique());
    }

    @Test
    public void testGetInexistant() {
        Client actual = ClientDAO.getByFormeJuridique("test_client_fake_forme_juridique");
        assertNull(actual);
    }

}
