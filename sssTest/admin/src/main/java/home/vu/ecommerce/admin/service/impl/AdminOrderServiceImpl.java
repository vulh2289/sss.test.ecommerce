package home.vu.ecommerce.admin.service.impl;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.OrderResult;
import home.vu.ecommerce.admin.service.AdminOrderService;
import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.enums.ShipmentStatus;
import home.vu.ecommerce.common.model.Order;

import java.util.List;

public class AdminOrderServiceImpl implements AdminOrderService {

    private OrderDao orderDao;

    // Constructor
    public AdminOrderServiceImpl() {
    }

    // Setters
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.admin.service.AdminOrderService#getOrders(home.vu.ecommerce.admin.model.CountsSortingPagination)
     */
    public OrderResult getOrders(CountsSortingPagination csp) {
        List<Order> orders = orderDao.getOrders(null);

        OrderResult result = new OrderResult();
        result.setOrders(orders);

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.admin.service.AdminOrderService#changeShipmentStatus(int, home.vu.ecommerce.common.enums.ShipmentStatus)
     */
    public void changeShipmentStatus(int orderDetailsId, ShipmentStatus status) {
        orderDao.updateShipmentStatus(orderDetailsId, status);
    }
}
