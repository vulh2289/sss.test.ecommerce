package home.vu.ecommerce.common.dao;

import home.vu.ecommerce.common.enums.ShipmentStatus;
import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.OrderDetail;
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

    /**
     * Get orders in more details
     * 
     * @param user
     * @return
     */
    public List<OrderDetail> getOrderDetails(User user);

    /**
     * Update shipment status of an order details
     * 
     * @param orderDetailsId
     * @param status
     */
    public void updateShipmentStatus(int orderDetailsId, ShipmentStatus status);

    /**
     * Get order by external payment id
     * 
     * @param paymentId
     * @return
     */
    public Order getOrder(String paymentId);

    /**
     * Update an order
     * 
     * @param order
     */
    public void updateOrder(Order order);
}
