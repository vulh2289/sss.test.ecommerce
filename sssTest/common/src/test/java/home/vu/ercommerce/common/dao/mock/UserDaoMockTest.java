package home.vu.ercommerce.common.dao.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import home.vu.ercommerce.common.dao.UserDao;
import home.vu.ercommerce.common.dao.mock.UserDaoMock;
import home.vu.ercommerce.common.model.User;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Bad practice is applied for this class tests.
 * 
 * Because we do not use any Database to store data, we cannot directly access to database to get actual data
 * 
 * @author Le Huy Vu
 *
 */
public class UserDaoMockTest {

    private static final int NO_OF_USERS = 10;
    // Fields
    private UserDao userDao;

    @Before
    public void setup() {
        userDao = new UserDaoMock(NO_OF_USERS);
    }

    @Test
    public void shouldCreateUser() throws Exception {

        // Given
        User user = new User();
        user.setActive(true);
        user.setEmail("vu+test@adoreboard.com");
        user.setFirstName("Vu+test");
        user.setLastName("Le");
        user.setRole(0);
        user.setSecret("$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.");
        user.setUserName("vu+test@adoreboard.com");

        assertEquals(NO_OF_USERS, userDao.getUsers(false).size()); // Bad practice: Should not user a Dao call to test another

        // When
        userDao.createUser(user);

        // Then
        assertEquals(NO_OF_USERS + 1, userDao.getUsers(false).size());
        User savedUser = userDao.getUser("vu+test@adoreboard.com");
        assertEquals("Vu+test", savedUser.getFirstName());
        assertEquals("Le", savedUser.getLastName());
        assertEquals(0, savedUser.getRole());

    }

    @Test
    public void shouldUpdateUser() throws Exception {

        // Given
        String userName = "vu+1@adoreboard.com";

        User user = new User();
        user.setActive(true);
        user.setEmail("vu+test@adoreboard.com");
        user.setFirstName("Vu+test");
        user.setLastName("Le");
        user.setRole(0);
        user.setSecret("new password");
        user.setUserName(userName);

        assertEquals("$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.", userDao.getUser(userName).getSecret());

        // When
        userDao.updateUser(user);

        // Then
        assertEquals("new password", userDao.getUser(userName).getSecret());
        assertEquals(NO_OF_USERS, userDao.getUsers(false).size());

    }

    @Test
    public void shouldDeleteUser() throws Exception {

        // Given
        String userName = "vu+1@adoreboard.com";
        assertEquals(NO_OF_USERS, userDao.getUsers(false).size());

        // When
        userDao.deleteUser(userName);

        // Then
        assertEquals(NO_OF_USERS - 1, userDao.getUsers(false).size());
        assertNull("This user should have been deleted", userDao.getUser(userName));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        // Given
        User user = new User();
        user.setActive(false);
        user.setEmail("vu+test@adoreboard.com");
        user.setFirstName("Vu+test");
        user.setLastName("Le");
        user.setRole(0);
        user.setSecret("$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.");
        user.setUserName("vu+test@adoreboard.com");

        userDao.createUser(user);

        // When
        List<User> users = userDao.getUsers(false);

        // Then
        assertTrue("This user must be selected", users.contains(user));

    }

    @Test
    public void shouldGetActiveUsersOnly() throws Exception {
        // Given
        User user = new User();
        user.setActive(false);
        user.setEmail("vu+test@adoreboard.com");
        user.setFirstName("Vu+test");
        user.setLastName("Le");
        user.setRole(0);
        user.setSecret("$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.");
        user.setUserName("vu+test@adoreboard.com");

        userDao.createUser(user);

        // When
        List<User> users = userDao.getUsers(true);

        // Then
        assertFalse("This user must NOT be selected", users.contains(user));

    }
}
