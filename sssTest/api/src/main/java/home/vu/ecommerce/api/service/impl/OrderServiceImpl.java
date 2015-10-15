package home.vu.ecommerce.api.service.impl;

import home.vu.ecommerce.api.exception.SSSTestApiException;
import home.vu.ecommerce.api.model.BasketItem;
import home.vu.ecommerce.api.model.OrderInput;
import home.vu.ecommerce.api.service.OrderService;
import home.vu.ecommerce.api.service.PaymentService;
import home.vu.ecommerce.common.constant.SSSTestConstants;
import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.enums.PaymentMethod;
import home.vu.ecommerce.common.enums.ShipmentStatus;
import home.vu.ecommerce.common.model.Item;
import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.OrderDetail;
import home.vu.ecommerce.common.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private ItemDao itemDao;
    private PaymentService paymentService;

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

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.OrderService#placeOrder(home.vu.ecommerce.common.model.User, home.vu.ecommerce.api.model.OrderInput)
     */
    public String placeOrder(User buyer, OrderInput orderInput) {

        float total = 0;

        List<OrderDetail> details = new ArrayList<OrderDetail>();

        for (BasketItem each : orderInput.getBasketItems()) {
            Item availableItem = itemDao.getItem(each.getItemId());
            if (availableItem.getQuantity() < each.getQuantity()) {
                throw new SSSTestApiException("Inventory is not sufficient!");
            }

            Item boughtItem = new Item(availableItem.getName(), availableItem.getPrice(), each.getQuantity(), availableItem.isActive());
            boughtItem.setId(availableItem.getId());

            OrderDetail orderDetail = new OrderDetail(boughtItem);
            details.add(orderDetail);

            total += availableItem.getPrice() * each.getQuantity();
        }

        Map<String, String> result = paymentService.makePayment(orderInput.getPaymentMethod(), orderInput.isDirectPayment(), total, "USD",
            orderInput.getPaymentDetails());

        Order newOrder = new Order(buyer, details);
        if (orderInput.isDirectPayment()) {
            newOrder.setPaid(true);
        }
        else {
            newOrder.setPaid(false);
            newOrder.setExternalId(result.get(SSSTestConstants.PAYMENT_ID));
            for (OrderDetail detail : newOrder.getDetails()) {
                detail.setShipmentStatus(ShipmentStatus.UNPAID);
            }
        }
        orderDao.createOrder(newOrder);

        return result.get(SSSTestConstants.APPROVAL_URL);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.OrderService#completeIndirectOrder(java.lang.String, java.util.Map)
     */
    public boolean completeIndirectOrder(PaymentMethod paymentMethod, Map<String, String> customInput) {
        boolean paid = paymentService.completeIndirectPayment(paymentMethod, customInput);
        Order order = orderDao.getOrder(customInput.get(SSSTestConstants.PAYMENT_ID));
        order.setPaid(paid);
        for (OrderDetail detail : order.getDetails()) {
            detail.setShipmentStatus(ShipmentStatus.PENDING);
        }
        orderDao.updateOrder(order);

        return paid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.OrderService#checkout(home.vu.ecommerce.common.model.User, java.util.List)
     */
    public List<BasketItem> checkout(User user, List<BasketItem> basketItems) {

        List<BasketItem> insuffcientItems = new ArrayList<BasketItem>();

        for (BasketItem each : basketItems) {
            Item availableItem = itemDao.getItem(each.getItemId());
            if (availableItem.getQuantity() < each.getQuantity()) {
                insuffcientItems.add(each);
            }
        }

        return insuffcientItems;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.OrderService#getOrderDetails(home.vu.ecommerce.common.model.User)
     */
    public List<OrderDetail> getOrderDetails(User user) {
        List<OrderDetail> returningDetails = new ArrayList<OrderDetail>();
        List<Order> orders = orderDao.getOrders(user);

        for (Order order : orders) {
            for (OrderDetail each : order.getDetails()) {
                returningDetails.add(each);
            }
        }

        return returningDetails;
    }

}
