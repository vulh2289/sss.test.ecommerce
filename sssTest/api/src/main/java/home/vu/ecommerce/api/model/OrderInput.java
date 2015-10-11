package home.vu.ecommerce.api.model;

/**
 * Wrapper object that is used when an API of placing order is called
 * 
 * @author Le Huy Vu
 *
 */
public class OrderInput {

    // Fields
    private int itemId;
    private int quantity;

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

}
