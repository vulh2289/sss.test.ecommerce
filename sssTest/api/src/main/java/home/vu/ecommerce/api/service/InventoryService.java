package home.vu.ecommerce.api.service;

import home.vu.ecommerce.common.model.Item;

import java.util.List;

public interface InventoryService {

    /**
     * @param sortBy
     * @param asc
     * @param offset
     * @param limit
     * @return
     */
    public List<Item> getItems(String sortBy, boolean asc, int offset, int limit);

    /**
     * @param id
     * @return
     */
    public Item getItem(int id);

}
