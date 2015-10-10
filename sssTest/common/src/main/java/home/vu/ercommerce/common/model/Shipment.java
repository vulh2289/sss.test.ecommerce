package home.vu.common.model;

import home.vu.common.enums.ShipmentStatus;

import java.util.List;

import org.joda.time.DateTime;

public class Shipment {

    private int id;
    private List<SaleRecord> boughtItems;
    private ShipmentStatus shipmentStatus;
    private DateTime createdAt;
    private DateTime updatedAt;

    // Constructor
    public Shipment(List<SaleRecord> boughtItems, ShipmentStatus shipmentStatus) {
        this.boughtItems = boughtItems;
        this.shipmentStatus = shipmentStatus;

        DateTime now = new DateTime();
        createdAt = now;
        updatedAt = now;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SaleRecord> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<SaleRecord> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
