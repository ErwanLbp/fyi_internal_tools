package dao;

import common.Role;
import common.Consultant;
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
        consultant = new Consultant("a", "b", "c", "d", role.getId_role());
        ConsultantDAO.insert(consultant);
    }

    @After
    public void deleteLigneBDD() {
        ConsultantDAO.delete(consultant.getUsername());
    }

    @Test
    public void testInsert() {
        consultant_insert = new Consultant("a_insert", "b_insert", "c_insert", "d_insert", role_insert.getId_role());
        assertTrue(ConsultantDAO.insert(consultant_insert));
        Consultant actual = ConsultantDAO.get(consultant_insert.getUsername());
        assert  actual != null;
        consultant_insert.setId(actual.getId());
        assertEquals(consultant_insert.getUsername(),actual.getUsername());
    }

    @Test
    public void testUpdate() {
        consultant_update = new Consultant(ConsultantDAO.get(consultant.getUsername()).getId(),"a_update","b_update","c_update","d_update",ConsultantDAO.get(consultant.getUsername()).getRole_id());
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
    public void testGet() {
        Consultant actual = ConsultantDAO.get(consultant.getUsername());
        assert actual != null;
        assertEquals(consultant.getUsername(), actual.getUsername());
    }

    @Test
    public void testGetIdInexistant() {
        Consultant actual = ConsultantDAO.get(-1);
        assertNull(actual);
    }

    @Test
    public void testGetUsernameInexistant() {
        Consultant actual = ConsultantDAO.get("###");
        assertNull(actual);
    }

    @Test
    public void testCheckLoginPasswordFake(){
        assertTrue(ConsultantDAO.insert(consultant));
        assertNotEquals(ConsultantDAO.get(consultant.getUsername()),"login faux");
    }

    @Test
    public void testCheckLoginPassword() {
        assertTrue(ConsultantDAO.insert(consultant));
        assertNotEquals(ConsultantDAO.get(consultant.getUsername()), consultant.getPassword());
    }

    @Test
    public void testIsInDb(){
        assertTrue(ConsultantDAO.isInDB(ConsultantDAO.get(consultant.getUsername()).getId()));
    }

    @Test
    public void testIsNotInDb(){
        assertFalse(ConsultantDAO.isInDB(-1));
    }

}
