package home.vu.ecommerce.common.dao.mock.json;

import home.vu.ecommerce.common.dao.UserDao;
import home.vu.ecommerce.common.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJsonMock extends AbstractJsonMockDao implements UserDao {

    public UserDaoJsonMock(String basePath, String jsonDbFile) {
        super(basePath, jsonDbFile);
    }

    /**
     * Update current id
     */
    public void updateCurrentId() {
        currentId = getLast(User.class) != null ? getLast(User.class).getId() : 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.dao.UserDao#createUser(home.vu.ecommerce.common.model.User)
     */
    public void createUser(User newUser) {
        List<User> allUsers = readDB(User.class);
        newUser.setId(++currentId);
        allUsers.add(newUser);
        writeDB(allUsers);
    }

    public User getUser(String userName) {
        List<User> allUsers = readDB(User.class);
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
     * @see home.vu.ecommerce.common.dao.UserDao#updateUser(home.vu.ecommerce.common.model.User)
     */
    public void updateUser(User updatedUser) {
        List<User> allUsers = readDB(User.class);
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            if (user.getUserName().equalsIgnoreCase(updatedUser.getUserName())) {
                user = updatedUser;
                allUsers.set(i, updatedUser);
                break;
            }
        }
        writeDB(allUsers);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.dao.UserDao#deleteUser(java.lang.String)
     */
    public void deleteUser(String userName) {
        List<User> allUsers = readDB(User.class);
        for (User user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                allUsers.remove(user);
                break;
            }
        }
        writeDB(allUsers);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.dao.UserDao#getUsers(boolean)
     */
    public List<User> getUsers(boolean activeOnly) {
        List<User> allUsers = readDB(User.class);
        List<User> users = new ArrayList<User>();
        for (User user : allUsers) {
            if (!activeOnly || (activeOnly && user.isActive())) {
                users.add(user);
            }
        }

        return users;
    }
}
