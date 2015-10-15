package home.vu.ecommerce.admin.service.impl;

import home.vu.ecommerce.admin.exception.SSSTestInventoryException;
import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.OrderResult;
import home.vu.ecommerce.admin.service.AdminOrderService;
import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.enums.ShipmentStatus;
import home.vu.ecommerce.common.model.Item;
import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.OrderDetail;

import java.util.List;

public class AdminOrderServiceImpl implements AdminOrderService {

    private OrderDao orderDao;
    private ItemDao itemDao;

    // Constructor
    public AdminOrderServiceImpl() {
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
    public void changeShipmentStatus(int orderId, int orderDetailsId, ShipmentStatus status) {
        if (status == ShipmentStatus.DISPATCHED) {
            Order order = orderDao.getOrder(orderId);
            for (OrderDetail details : order.getDetails()) {
                if (details.getId() == orderDetailsId) {
                    Item item = itemDao.getItem(details.getItem().getId());

                    // Reduce quantity
                    if (item.getQuantity() >= details.getItem().getQuantity()) {
                        itemDao.updateItemDetails(details.getItem().getId(), null, -1,
                            item.getQuantity() - details.getItem().getQuantity(), null);
                    }
                    else {
                        throw new SSSTestInventoryException("Inventory is not sufficient");
                    }
                }
            }

        }

        // Update shipment status
        orderDao.updateShipmentStatus(orderDetailsId, status);
    }
}
