package dao;

import common.Client;
import org.junit.*;

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

    private static Client client = new Client("test_client_rs", "test_client_fj", "test_client_siret", "test_client_num_tva", "test_client_rcs", 1, "test_client_adresse_rue", 95000, "test_client_adresse_ville", "test_client_telephone", 1000000, "test_client_ville_inscription", "test_client_representant_nom", "test_client_representant_fonction", "test_client_respo_client_tel", "test_client_contact_achats_nom", "test_client_contact_achats_tel", "test_client_respo_fournisseur_nom");
    private static Client client_insert = new Client("test_client_rs_insert", "test_client_fj_insert", "test_client_siret_insert", "test_client_num_tva_insert", "test_client_rcs_insert", 2, "test_client_adresse_rue__insert", 95000, "test_client_adresse_ville_insert", "test_client_telephone_insert", 1000000, "test_client_ville_inscription_insert", "test_client_representant_nom_insert", "test_client_representant_fonction_insert", "test_client_respo_client_tel_insert", "test_client_contact_achats_nom_insert", "test_client_contact_achats_tel_insert", "test_client_respo_fournisseur_nom_insert");
    private static Client client_update = new Client("test_client_raison_sociale_update", "test_client_forme_juridique_update", "test_client_siret_update", "test_client_num_tva_update", "test_client_rcs_update", 1, "test_client_adresse_rue_update", 95000, "test_client_adresse_ville_update", "test_client_telephone_update", 1000000, "test_client_ville_inscription_update", "test_client_representant_nom_update", "test_client_representant_fonction_update", "test_client_respo_client_tel_update", "test_client_contact_achats_nom_update", "test_client_contact_achats_tel_update", "test_client_respo_fournisseur_nom_update");
    private static Client client_fake_id = new Client(70,"test_client_rs", "test_client_fj", "test_client_siret", "test_client_num_tva", "test_client_rcs", 1, "test_client_adresse_rue", 95000, "test_client_adresse_ville", "test_client_telephone", 1000000, "test_client_ville_inscription", "test_client_representant_nom", "test_client_representant_fonction", "test_client_respo_client_tel", "test_client_contact_achats_nom", "test_client_contact_achats_tel", "test_client_respo_fournisseur_nom");

    @Before
    public void insertLigneBDD() {
        ClientDAO.insertComplet(client);
    }

    @After
    public void deleteLigneBDD() {
        ClientDAO.deleteById(client.getId());
    }

    @AfterClass
    public static void suppressionsInsertTests() {
        ClientDAO.deleteById(client_insert.getId());
        ClientDAO.deleteById(client_update.getId());
    }

    @Test
    public void testInsert() {
        assertTrue(ClientDAO.insertComplet(client_insert));
        Client actual = ClientDAO.getByFormeJuridique(client_insert.getForme_juridique());
        assert actual != null;
        assertEquals(client_insert.getForme_juridique(), actual.getForme_juridique());
    }

    @Test
    public void testUpdate() {
        assertTrue(ClientDAO.updateByFormeJuridique(client.getForme_juridique(),client_update));
        Client actual = ClientDAO.getByFormeJuridique(client_update.getForme_juridique());
        assert actual != null;
        assertEquals(client_update.getForme_juridique(), actual.getForme_juridique());
    }

    @Test
    public void testDelete() {
        assertTrue(ClientDAO.deleteByFormeJuridique(client.getForme_juridique()));
        Client actual = ClientDAO.getByFormeJuridique(client.getForme_juridique());
        assertNull(actual);
    }


    @Test
    public void testGetInexistant() {
        Client actual = ClientDAO.getById(client_fake_id.getId());
        assertNull(actual);
    }

}
