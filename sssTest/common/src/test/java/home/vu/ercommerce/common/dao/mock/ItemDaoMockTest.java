package home.vu.ercommerce.common.dao.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.dao.mock.ItemDaoMock;
import home.vu.ecommerce.common.model.Item;
import home.vu.ecommerce.common.util.SSSTestUtils;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests of ItemDaoMock
 * 
 * @author Le Huy Vu
 *
 */
public class ItemDaoMockTest {

    private static final int AMOUNT = 10;
    private ItemDao itemDao;

    @Before
    public void setup() throws Exception {
        itemDao = new ItemDaoMock(AMOUNT);
    }

    @Test
    public void shouldCreateNewItem() throws Exception {

        // Given
        Item item = new Item("New item", 1.49f, 50, true);

        List<Item> allItems = itemDao.queryItems(null, true, 0, -1);
        assertEquals(AMOUNT, allItems.size());

        // When
        itemDao.createItem(item);

        // Then
        allItems = itemDao.queryItems(null, true, 0, -1);
        assertEquals(AMOUNT + 1, allItems.size());
        Item createdItem = itemDao.getItem(AMOUNT + 1);
        assertEquals("New item", createdItem.getName());

    }

    @Test
    public void shouldUpdateAnItem() throws Exception {

        // Given
        float randomPrice = SSSTestUtils.randFloat(0.5f, 5.0f);
        int randomQuantity = SSSTestUtils.randInt(1, 10);

        Item selectedItem = itemDao.getItem(AMOUNT); // last item

        assertEquals("Item 10", selectedItem.getName());
        assertTrue("Should be active", selectedItem.isActive());

        // When
        itemDao.updateItemDetails(AMOUNT, "New Item", randomPrice, randomQuantity, null);

        // Then
        selectedItem = itemDao.getItem(AMOUNT);
        assertEquals("New Item", selectedItem.getName());
        assertEquals(randomPrice, selectedItem.getPrice(), 0.001);
        assertEquals(randomQuantity, selectedItem.getQuantity());
        assertTrue("Should still be active", selectedItem.isActive());
    }
}
