package home.vu.ecommerce.common.dao.mock.json;

import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemDaoJsonMock extends AbstractJsonMockDao implements ItemDao {

    public ItemDaoJsonMock(String basePath, String jsonDbFile) {
        super(basePath, jsonDbFile);
    }

    /**
     * Update current id
     */
    public void updateCurrentId() {
        currentId = getLast(Item.class) != null ? getLast(Item.class).getId() : 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.dao.ItemDao#createItem(home.vu.ecommerce.common.model.Item)
     */
    public void createItem(Item item) {
        List<Item> allItems = readDB(Item.class);
        item.setId(++currentId);
        allItems.add(item);
        writeDB(allItems);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#updateItemDetails(int, java.lang.String, java.lang.Float, java.lang.Long, java.lang.Boolean)
     */
    public void updateItemDetails(int id, String name, float price, long quantity, Boolean active) {
        List<Item> allItems = readDB(Item.class);
        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            if (item.getId() == id) {

                // Only update if it is different from null
                if (name != null) {
                    item.setName(name);
                }

                if (price != -1) {
                    item.setPrice(price);
                }

                if (quantity != -1) {
                    item.setQuantity(quantity);
                }

                if (active != null) {
                    item.setActive(active);
                }

                allItems.set(i, item);
                break;
            }
        }
        writeDB(allItems);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#deleteItem(int)
     */
    public void deleteItem(int id) {
        List<Item> allItems = readDB(Item.class);
        for (Item item : allItems) {
            if (item.getId() == id) {
                allItems.remove(item);
                break;
            }
        }
        writeDB(allItems);

    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#getItem(int)
     */
    public Item getItem(int id) {
        List<Item> allItems = readDB(Item.class);
        for (Item item : allItems) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#queryItems(java.lang.String, boolean, int, int)
     */
    public List<Item> queryItems(String sortBy, boolean asc, int offset, int limit) {
        List<Item> allItems = readDB(Item.class);
        if (sortBy == null) {
            sortBy = Comparable.name.toString();
        }

        if (limit == -1 || limit > allItems.size()) {
            limit = allItems.size();
        }

        Comparator<Item> customComparator = new CustomItemComparator(Comparable.valueOf(sortBy), asc);
        Collections.sort(allItems, customComparator);

        List<Item> returnedList = new ArrayList<Item>();
        for (int i = 0; i < limit; i++) {
            if (!(offset + i >= allItems.size())) {
                returnedList.add(allItems.get(offset + i));
            }
            else {
                break;
            }
        }
        return returnedList;
    }

    // Private classes
    /**
     * Custom Comparator for Item object
     * 
     * @author Le Huy Vu
     *
     */
    private class CustomItemComparator implements Comparator<Item> {

        private Comparable comparable;
        private boolean asc;

        public CustomItemComparator(Comparable comparable, boolean asc) {
            this.comparable = comparable;
            this.asc = asc;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Item i1, Item i2) {
            switch (this.comparable) {
                case active:
                    return (i1.isActive() == i2.isActive() ? 0 : (asc ? 1 : -1));
                case name:
                    return this.asc ? (i1.getName().compareTo(i2.getName())) : (i2.getName().compareTo(i1.getName()));
                case price:
                    return (i1.getPrice() == i2.getPrice() ? 0 : (asc && i1.getPrice() > i2.getPrice() ? 1 : -1));
                case quantity:
                    return (i1.getQuantity() == i2.getQuantity() ? 0 : (asc && i1.getQuantity() > i2.getQuantity() ? 1 : -1));
                default:
                    return (i1.getId() == i2.getId() ? 0 : (asc && i1.getId() > i2.getId() ? 1 : -1));
            }
        }
    }

    /**
     * Sorting case
     * 
     * @author Le Huy Vu
     *
     */
    private enum Comparable {
        id, name, price, quantity, active;
    }

}
