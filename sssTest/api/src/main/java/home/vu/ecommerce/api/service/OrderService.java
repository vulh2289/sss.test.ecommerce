package home.vu.ecommerce.api.service;

import home.vu.ecommerce.api.model.BasketItem;
import home.vu.ecommerce.api.model.OrderInput;
import home.vu.ecommerce.common.enums.PaymentMethod;
import home.vu.ecommerce.common.model.OrderDetail;
import home.vu.ecommerce.common.model.User;

import java.util.List;
import java.util.Map;

/**
 * Interface that allows system to manage order from users
 * 
 * @author Le Huy Vu
 *
 */
public interface OrderService {

    /**
     * Place an order for a user with a specified payment and items
     * 
     * @param buyer
     */
    public String placeOrder(User buyer, OrderInput orderInput);

    /**
     * Complete an indirect order
     * 
     * @param method
     * @param input
     * @return
     */
    public boolean completeIndirectOrder(PaymentMethod method, Map<String, String> input);

    /**
     * @param user
     * @return
     */
    public List<OrderDetail> getOrderDetails(User user);

    /**
     * @param user
     * @param basketItems
     * @return
     */
    public List<BasketItem> checkout(User user, List<BasketItem> basketItems);

}
