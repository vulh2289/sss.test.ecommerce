package home.vu.ecommerce.common.model;

import java.util.List;

import org.joda.time.DateTime;

public class Order {

    // Fields
    private int id;
    private User buyer;
    private List<OrderDetail> details;
    private DateTime createdAt;

    // Constructor
    public Order(User buyer, List<OrderDetail> details) {
        this.buyer = buyer;
        this.details = details;
        createdAt = new DateTime();
    }

    // Getters & Setters
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
