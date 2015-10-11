package home.vu.ecommerce.common.model;

import org.joda.time.DateTime;

public class SaleRecord {

    private int id;
    private User buyer;
    private Item item;
    private DateTime createdAt;

    // Constructor
    public SaleRecord(User buyer, Item item) {
        this.buyer = buyer;
        this.item = item;
        createdAt = new DateTime();
    }

    // Getters & Setters
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
