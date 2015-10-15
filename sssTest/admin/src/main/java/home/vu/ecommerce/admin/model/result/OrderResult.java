package home.vu.ecommerce.admin.model.result;

import home.vu.ecommerce.common.model.Order;

import java.util.List;

public class OrderResult {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
