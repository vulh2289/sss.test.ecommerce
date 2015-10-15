package home.vu.ercommerce.common.dao.mock.json;

import home.vu.ecommerce.common.dao.UserDao;
import home.vu.ecommerce.common.dao.mock.json.UserDaoJsonMock;
import home.vu.ecommerce.common.model.User;

import org.junit.Before;
import org.junit.Test;

public class UserDaoJsonMockTest extends AbstractJsonMockDaoTest {

    private static final String DUMB_DB_USERS_TEST_JSON = "/dumb_db/users_test.json";
    //
    private UserDao userDao;

    @Before
    public void setup() throws Exception {
        userDao = new UserDaoJsonMock(DUMB_DB_USERS_TEST_JSON, null);
    }

    @Test
    public void shouldCreateUser() throws Exception {

        String expectedJson =//@formatter:off
                "[" +
                "   {" +
                "         \"id\": 1," +
                "         \"firstName\": \"Frances\"," +
                "         \"lastName\": \"Jones\"," +
                "         \"userName\": \"fjones0@yale.edu\"," +
                "         \"secret\": \"$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.\"," +
                "         \"active\": false," +
                "         \"role\": 1" +
                "   }," +
                "   {" +
                "         \"id\": 2," +
                "         \"firstName\": \"Vu+test\"," +
                "         \"lastName\": \"Le\"," +
                "         \"userName\": \"vu+test@adoreboard.com\"," +
                "         \"secret\": \"$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.\"," +
                "         \"active\": true," +
                "         \"email\": \"vu+test@adoreboard.com\"," + 
                "         \"role\": 0" +
                "   }" +
                "]"; //@formatter:on

        // Given
        User user = new User();
        user.setActive(true);
        user.setEmail("vu+test@adoreboard.com");
        user.setFirstName("Vu+test");
        user.setLastName("Le");
        user.setRole(0);
        user.setSecret("$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.");
        user.setUserName("vu+test@adoreboard.com");

        // When
        userDao.createUser(user);

        // Then
        String actualJson = super.readJson(DUMB_DB_USERS_TEST_JSON);
        // assertEquals(expectedJson, actualJson, true);
    }
}
