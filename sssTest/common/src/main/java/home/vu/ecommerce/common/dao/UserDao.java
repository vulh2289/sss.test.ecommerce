package home.vu.ecommerce.common.dao;

import home.vu.ecommerce.common.model.User;

import java.util.List;

/**
 * Interface that provide User CRUD calls to the database
 * 
 * @author Le Huy Vu
 *
 */
public interface UserDao {

    /**
     * Create an user
     * 
     * @param newUser
     */
    public void createUser(User newUser);

    /**
     * Get a user which has specified userName
     * 
     * @param userName
     * @return
     */
    public User getUser(String userName);

    /**
     * Update an user
     * 
     * @param user
     */
    public void updateUser(User user);

    /**
     * delete a user which has specified userName
     * 
     * @param userName
     */
    public void deleteUser(String userName);

    /**
     * Get either active only users or all users
     * 
     * @param activeOnly
     * @return
     */
    public List<User> getUsers(boolean activeOnly);
}
