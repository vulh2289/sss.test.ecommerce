package home.vu.ecommerce.common.dao;

import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.User;

import java.util.List;

/**
 * Interface that provide Order CRUD calls to the database
 * 
 * @author Le Huy Vu
 *
 */
public interface OrderDao {

    /**
     * Create an order
     * 
     * @param newUser
     */
    public void createOrder(Order order);

    /**
     * Get an order which has specified id
     * 
     * @param id
     * @return
     */
    public Order getOrder(int id);

    /**
     * Get all sale records of a users.
     * 
     * Leave user null to get all records
     * 
     * @param activeOnly
     * @return
     */
    public List<Order> getOrders(User user);
}
