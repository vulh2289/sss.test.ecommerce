package home.vu.ecommerce.common.dao.mock;

import home.vu.ecommerce.common.dao.UserDao;
import home.vu.ecommerce.common.model.User;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class UserDaoMock implements UserDao {

    private List<User> allUsers;
    private int currentId = 0;

    // Constructor

    public UserDaoMock(int amount) {
        allUsers = initFakeUsers(amount);
    }

    // CRUD methods

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.UserDao#createUser(home.vu.common.model.User)
     */
    public void createUser(User newUser) {
        newUser.setId(++currentId);
        allUsers.add(newUser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.UserDao#getUser(java.lang.String)
     */
    public User getUser(String userName) {
        for (User user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.UserDao#updateUser(home.vu.common.model.User)
     */
    public void updateUser(User updatedUser) {
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            if (user.getUserName().equalsIgnoreCase(updatedUser.getUserName())) {
                user = updatedUser;
                allUsers.set(i, updatedUser);
                break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.UserDao#deleteUser(java.lang.String)
     */
    public void deleteUser(String userName) {
        for (User user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                this.allUsers.remove(user);
                break;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.UserDao#getUsers(boolean)
     */
    public List<User> getUsers(boolean activeOnly) {
        List<User> users = new ArrayList<User>();
        for (User user : allUsers) {
            if (!activeOnly || (activeOnly && user.isActive())) {
                users.add(user);
            }
        }

        return users;
    }

    // Private helpers

    /**
     * Create a dumb list of users
     * 
     * @param amount
     * @return
     */
    private List<User> initFakeUsers(int amount) {
        List<User> fakeUsers = new ArrayList<User>();
        for (int i = 1; i <= amount; i++) {
            User user = new User();
            user.setId(i);
            user.setActive(true);
            user.setCreatedOn(new DateTime());
            user.setEmail("vu+" + i + "@adoreboard.com");
            user.setFirstName("Vu+" + i);
            user.setLastName("Le");
            user.setRole(0);
            user.setSecret("$2a$04$/IaSz.IZRI5kgtgkGORUIu.pWUDACI69FHYn2343belVzhenaK.j.");
            user.setUserName("vu+" + i + "@adoreboard.com");

            fakeUsers.add(user);
            currentId = i;
        }

        return fakeUsers;
    }

}
