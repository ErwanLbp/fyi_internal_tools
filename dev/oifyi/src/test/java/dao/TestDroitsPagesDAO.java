package dao;

import common.DroitsPages;
import common.MappingUrlFichier;
import common.Role;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * <h1>PACKAGE_NAME TestDroitsPagesDAO</h1>
 * TODO Description
 *
 * @author Erwan
 * @version 1.0
 * @since 19-10-2016
 */
public class TestDroitsPagesDAO {

    private static Role role;
    private static MappingUrlFichier muf;

    private static Role role_insert;
    private static MappingUrlFichier muf_insert;

    @BeforeClass
    public static void insertsRoleEtPage() {
        role = new Role("role_test_droits_pages");
        RoleDAO.insert(role);
        Role role1 = RoleDAO.get(role.getLibelle());

        muf = new MappingUrlFichier("muf_page_droits_pages", "muf_mode_droits_pages", "muf_fichier_droits_pages");
        MappingUrlFichierDAO.insert(muf);
        MappingUrlFichier muf1 = MappingUrlFichierDAO.getMuf(muf.getNomPage(), muf.getNomMode());

        assert role1 != null;
        assert muf1 != null;
        role.setId_role(role1.getId_role());
        muf.setId_muf(muf1.getId_muf());
    }

    @AfterClass
    public static void deletesRoleEtPage() {
        DroitsPagesDAO.deleteOne(new DroitsPages(muf_insert.getId_muf(), role_insert.getId_role()));

        RoleDAO.delete(role.getLibelle());
        RoleDAO.delete(role_insert.getLibelle());

        MappingUrlFichierDAO.deleteByNomPageNomMode(muf.getNomPage(), muf.getNomMode());
        MappingUrlFichierDAO.deleteByNomPageNomMode(muf_insert.getNomPage(), muf_insert.getNomMode());
    }

    @Before
    public void insertLigneBDD() {
        DroitsPagesDAO.insert(new DroitsPages(muf.getId_muf(), role.getId_role()));
    }

    @After
    public void deleteLigneBDD() {
        DroitsPagesDAO.deleteOne(new DroitsPages(muf.getId_muf(), role.getId_role()));
    }

    @Test
    public void testInsert() {
        role_insert = new Role("role_test_droits_pages_2");
        RoleDAO.insert(role_insert);
        Role role1 = RoleDAO.get(role_insert.getLibelle());

        muf_insert = new MappingUrlFichier("muf_page_droits_pages_2", "muf_mode_droits_pages_2", "muf_fichier_droits_pages_2");
        MappingUrlFichierDAO.insert(muf_insert);
        MappingUrlFichier muf1 = MappingUrlFichierDAO.getMuf(muf_insert.getNomPage(), muf_insert.getNomMode());

        assert role1 != null;
        assert muf1 != null;
        role_insert.setId_role(role1.getId_role());
        muf_insert.setId_muf(muf1.getId_muf());

        assertTrue(DroitsPagesDAO.insert(new DroitsPages(muf_insert.getId_muf(), role_insert.getId_role())));
        assertTrue(DroitsPagesDAO.isInDB(new DroitsPages(muf_insert.getId_muf(), role_insert.getId_role())));
    }

    @Test
    public void testDelete() {
        DroitsPages dp = new DroitsPages(muf.getId_muf(), role.getId_role());
        assertTrue(DroitsPagesDAO.deleteOne(dp));
        assertFalse(DroitsPagesDAO.isInDB(dp));
    }

    @Test
    public void testIsInDBExistant() {
        assertTrue(DroitsPagesDAO.isInDB(new DroitsPages(muf.getId_muf(), role.getId_role())));
    }

    @Test
    public void testIsInDBInExistant() {
        assertFalse(DroitsPagesDAO.isInDB(new DroitsPages(-1, -1)));
    }

    @Test
    public void testGetPages() {
        List<MappingUrlFichier> actual = DroitsPagesDAO.getPages(role.getId_role());
        assert actual != null;
        assertEquals(1, actual.size());
        assertEquals(muf.getId_muf(), actual.get(0).getId_muf());
    }

    @Test
    public void testGetRoles() {
        List<Role> actual = DroitsPagesDAO.getRoles(muf.getId_muf());
        assert actual != null;
        assertEquals(1, actual.size());
        assertEquals(role.getId_role(), actual.get(0).getId_role());
    }
}
