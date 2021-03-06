package dao;

import common.Consultant;
import common.Role;
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
public class TestConsultantDAO {

    private static Role role = new Role("a");
    private static Role role_insert = new Role("a_insert");

    private static Consultant consultant;
    private static Consultant consultant_insert;
    private static Consultant consultant_update;

    @BeforeClass
    public static void insertsAvantClasse() {
        RoleDAO.insert(role);
        role.setId_role(RoleDAO.get(role.getLibelle()).getId_role());
        RoleDAO.insert(role_insert);
        role_insert.setId_role(RoleDAO.get(role_insert.getLibelle()).getId_role());
    }

    @AfterClass
    public static void deletesApresClasse() {
        ConsultantDAO.delete(consultant.getId());
        ConsultantDAO.delete(consultant_insert.getId());
        ConsultantDAO.delete(consultant_update.getId());
        RoleDAO.delete(role.getLibelle());
        RoleDAO.delete(role_insert.getLibelle());
    }


    @Before
    public void insertLigneBDD() {
       // consultant = new Consultant("a", "b", "c", "d", role.getId_role());
        consultant = new Consultant("a", "b", "c", "d");

        ConsultantDAO.insert(consultant);
        consultant.setId(ConsultantDAO.get(consultant.getUsername()).getId());
    }

    @After
    public void deleteLigneBDD() {
        ConsultantDAO.delete(consultant.getUsername());
    }

    @Test
    public void testInsert() {
//        consultant_insert = new Consultant("a_insert", "b_insert", "c_insert", "d_insert", role_insert.getId_role());
        consultant_insert = new Consultant("a_insert", "b_insert", "c_insert", "d_insert");

        assertTrue(ConsultantDAO.insert(consultant_insert));
        Consultant actual = ConsultantDAO.get(consultant_insert.getUsername());
        assert actual != null;
        consultant_insert.setId(actual.getId());
        assertEquals(consultant_insert.getUsername(), actual.getUsername());
    }

    @Test
    public void testUpdate() {
       // consultant_update = new Consultant(ConsultantDAO.get(consultant.getUsername()).getId(), "a_update", "b_update", "c_update", "d_update", ConsultantDAO.get(consultant.getUsername()).getRole_id());
        consultant_update = new Consultant(ConsultantDAO.get(consultant.getUsername()).getId(), "a_update", "b_update", "c_update", "d_update");

        assertTrue(ConsultantDAO.update(consultant_update));
        Consultant actual = ConsultantDAO.get(consultant_update.getId());
        assert actual != null;
        assertEquals(consultant_update.getId(), actual.getId());
    }


    @Test
    public void testDeleteByUsername() {
        assertTrue(ConsultantDAO.delete(consultant.getUsername()));
        Consultant actual = ConsultantDAO.get(consultant.getId());
        assertNull(actual);
    }

    @Test
    public void testGetByUsername() {
        Consultant actual = ConsultantDAO.get(consultant.getUsername());
        assert actual != null;
        assertEquals(consultant.getUsername(), actual.getUsername());
    }

    @Test
    public void testGetByUsernameInexistant() {
        assertNull(ConsultantDAO.get(consultant.getUsername() + "fake"));
    }

    @Test
    public void testGetById() {
        Consultant actual = ConsultantDAO.get(consultant.getId());
        assert actual != null;
        assertEquals(consultant.getId(), actual.getId());
    }

    @Test
    public void testGetIdInexistant() {
        assertNull(ConsultantDAO.get(-1));
    }

    @Test
    public void testCheckLoginPasswordFake() {
        assertFalse(ConsultantDAO.checkLoginPassword("###login", "###pass"));
    }

    @Test
    public void testCheckLoginPassword() {
        assertTrue(ConsultantDAO.checkLoginPassword(consultant.getUsername(), consultant.getPassword()));
    }

    @Test
    public void testIsInDb() {
        assertTrue(ConsultantDAO.isInDB(consultant.getId()));
    }

    @Test
    public void testIsNotInDb() {
        assertFalse(ConsultantDAO.isInDB(-1));
    }

}
