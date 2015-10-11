package home.vu.ecommerce.api.service.impl;

import home.vu.ecommerce.api.model.OrderInput;
import home.vu.ecommerce.api.service.OrderService;
import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.model.Item;
import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.OrderDetail;
import home.vu.ecommerce.common.model.User;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private ItemDao itemDao;

    // Constructor
    public OrderServiceImpl() {
    }

    // Setters
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.OrderService#placeOrder(home.vu.ecommerce.common.model.User, java.util.List)
     */
    public boolean placeOrder(User buyer, List<OrderInput> order) {

        List<OrderDetail> details = new ArrayList<OrderDetail>();
        for (OrderInput each : order) {
            Item availableItem = itemDao.getItem(each.getItemId());
            if (availableItem.getQuantity() < each.getQuantity()) {
                return false;
            }

            Item boughtItem = new Item(availableItem.getName(), availableItem.getPrice(), each.getQuantity(), availableItem.isActive());
            boughtItem.setId(availableItem.getId());

            OrderDetail orderDetail = new OrderDetail(boughtItem);
            details.add(orderDetail);
        }

        Order newOrder = new Order(buyer, details);
        orderDao.createOrder(newOrder);

        return true;
    }
}
