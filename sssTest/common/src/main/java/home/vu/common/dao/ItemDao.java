package home.vu.common.dao;

import home.vu.common.model.Item;

import java.util.List;

/**
 * Interface that provides CRUD communication to databaseda.
 * 
 * @author Le Huy Vu
 *
 */
public interface ItemDao {

    /**
     * Create new item
     * 
     * @param item
     */
    public void createItem(Item item);

    /**
     * Update an item specified by @param id with the following details
     * 
     * @param name
     * @param price
     * @param quantity
     * @param active
     */
    public void updateItemDetails(int id, String name, Float price, Long quantity, Boolean active);

    /**
     * Delete item specified by id
     * 
     * @param id
     */
    public void deleteItem(int id);

    /**
     * Get item specified by id
     * 
     * @param id
     * @return
     */
    public Item getItem(int id);

    /**
     * Get items
     * 
     * @param sortBy
     * @param asc
     * @param offset
     * @param limit
     * @return
     */
    public List<Item> queryItems(String sortBy, boolean asc, int offset, int limit);

}
