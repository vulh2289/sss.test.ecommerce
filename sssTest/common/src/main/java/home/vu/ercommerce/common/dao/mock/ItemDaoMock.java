package home.vu.ercommerce.common.dao.mock;

import home.vu.ercommerce.common.dao.ItemDao;
import home.vu.ercommerce.common.model.Item;
import home.vu.ercommerce.common.util.SSSTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Mock database of items
 * 
 * @author Le Huy Vu
 *
 */
public class ItemDaoMock implements ItemDao {

    // Fields
    private List<Item> allItems;
    private int currentId = 0;

    // Constructor

    public ItemDaoMock(int amount) {
        allItems = initFakeItems(amount);
    }

    public void createItem(Item item) {
        item.setId(++currentId);
        allItems.add(item);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#updateItemDetails(int, java.lang.String, java.lang.Float, java.lang.Long, java.lang.Boolean)
     */
    public void updateItemDetails(int id, String name, float price, long quantity, Boolean active) {
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#deleteItem(int)
     */
    public void deleteItem(int id) {
        for (Item item : allItems) {
            if (item.getId() == id) {
                this.allItems.remove(item);
                break;
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ItemDao#getItem(int)
     */
    public Item getItem(int id) {
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

    // Private methods

    /**
     * Create dumb data of items
     * 
     * @param amount
     * @return
     */
    private List<Item> initFakeItems(int amount) {
        List<Item> fakeItems = new ArrayList<Item>();

        for (int i = 1; i <= amount; i++) {
            Item item = new Item("Item " + i, SSSTestUtils.randFloat(0.5f, 10.0f), SSSTestUtils.randInt(1, 1000), true);
            item.setId(i);
            fakeItems.add(item);
            currentId = i;
        }

        return fakeItems;
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
            }
            return 0;

        }
    }

    /**
     * Sorting case
     * 
     * @author Le Huy Vu
     *
     */
    private enum Comparable {
        name, price, quantity, active;
    }

}
