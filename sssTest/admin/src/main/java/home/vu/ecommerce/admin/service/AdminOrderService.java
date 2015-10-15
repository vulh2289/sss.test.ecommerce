package home.vu.ecommerce.admin.service;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.OrderResult;
import home.vu.ecommerce.common.enums.ShipmentStatus;

public interface AdminOrderService {

    /**
     * @param csp
     * @return
     */
    public OrderResult getOrders(CountsSortingPagination csp);

    /**
     * @param orderId
     * @param orderDetailsId
     * @param status
     */
    public void changeShipmentStatus(int orderId, int orderDetailsId, ShipmentStatus status);
}
