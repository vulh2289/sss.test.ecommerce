package home.vu.ecommerce.api.service.impl;

import home.vu.ecommerce.api.service.InventoryService;
import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.model.Item;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    // Fields
    private ItemDao itemDao;

    // Constructor
    public InventoryServiceImpl() {
    }

    // Setters

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.InventoryService#getItems(java.lang.String, boolean, int, int)
     */
    public List<Item> getItems(String sortBy, boolean asc, int offset, int limit) {
        return itemDao.queryItems(sortBy, asc, offset, limit);
    }

}
