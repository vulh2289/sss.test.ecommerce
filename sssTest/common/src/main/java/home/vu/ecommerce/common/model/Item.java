package home.vu.ecommerce.common.model;

import java.io.Serializable;

/**
 * Object that holds a simple inventory item
 * 
 * @author Le Huy Vu
 *
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 3348220543717570251L;

    // Fields
    private int id;
    private String name;
    private float price;
    private long quantity;
    private boolean active;

    // Constructor
    public Item(String name, float price, long quantity, boolean active) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.active = active;
    }

    public Item() {
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
