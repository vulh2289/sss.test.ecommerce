package home.vu.ecommerce.api.model;

/**
 * Wrapper object that is used when an API of placing order is called
 * 
 * @author Le Huy Vu
 *
 */
public class BasketItem {

    // Fields
    private int itemId;
    private int quantity;
    private String itemName;
    private float price;

    // Getters & Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
