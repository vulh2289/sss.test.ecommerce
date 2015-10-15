package home.vu.ecommerce.common.model;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

public class Order implements Serializable {

    private static final long serialVersionUID = -9038021274281113389L;

    // Fields
    private int id;
    private String externalId;
    private User buyer;
    private List<OrderDetail> details;
    private DateTime createdAt;
    private float total;
    private boolean paid;

    // Constructor
    public Order(User buyer, List<OrderDetail> details) {
        this.buyer = buyer;
        this.details = details;
        createdAt = new DateTime();
    }

    public Order() {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
