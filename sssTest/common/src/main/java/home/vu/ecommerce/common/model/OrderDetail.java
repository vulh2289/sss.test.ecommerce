package home.vu.ecommerce.common.model;

import home.vu.ecommerce.common.enums.ShipmentStatus;

import org.joda.time.DateTime;

public class OrderDetail {

    // Fields
    private int id;
    private Item item;
    private ShipmentStatus shipmentStatus;
    private DateTime updatedAt;

    // Constructors
    public OrderDetail(Item item, ShipmentStatus shipmentStatus, DateTime updatedAt) {
        this.item = item;
        this.shipmentStatus = shipmentStatus;
        this.updatedAt = updatedAt;
    }

    public OrderDetail(Item boughtItem) {
        this(boughtItem, ShipmentStatus.PENDING, new DateTime());
    }

    public OrderDetail() {
    }

    // Getters & Setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
