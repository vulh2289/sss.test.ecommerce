package home.vu.ecommerce.api.service;

import home.vu.ecommerce.api.model.OrderInput;
import home.vu.ecommerce.common.model.User;

import java.util.List;

/**
 * Interface that allows system to manage order from users
 * 
 * @author Le Huy Vu
 *
 */
public interface OrderService {

    /**
     * Place an order for a user with a specified item and its quantity
     * 
     * @param buyer
     * @param orderId
     * @param quantity
     */
    public boolean placeOrder(User buyer, List<OrderInput> order);

}
