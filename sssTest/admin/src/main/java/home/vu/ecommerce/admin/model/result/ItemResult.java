package home.vu.ecommerce.admin.model.result;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.common.model.Item;

import java.util.List;

public class ItemResult {

    // Query
    private List<Item> items;
    // Counts, sorting and pagination object
    private CountsSortingPagination csp;

    // Constructor
    public ItemResult() {
    }

    // Getters & Setters
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public CountsSortingPagination getCsp() {
        return csp;
    }

    public void setCsp(CountsSortingPagination csp) {
        this.csp = csp;
    }

}
