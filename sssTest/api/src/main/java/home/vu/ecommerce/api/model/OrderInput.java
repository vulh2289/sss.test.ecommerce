package home.vu.ecommerce.api.model;

import home.vu.ecommerce.common.enums.PaymentMethod;

import java.util.List;
import java.util.Map;

public class OrderInput {

    private PaymentMethod paymentMethod;
    private boolean directPayment;
    private Map<String, String> paymentDetails;
    private List<BasketItem> basketItems;

    // Empty Constructor
    public OrderInput() {
    }

    // Getters & Setters
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Map<String, String> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Map<String, String> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public boolean isDirectPayment() {
        return directPayment;
    }

    public void setDirectPayment(boolean directPayment) {
        this.directPayment = directPayment;
    }

}
