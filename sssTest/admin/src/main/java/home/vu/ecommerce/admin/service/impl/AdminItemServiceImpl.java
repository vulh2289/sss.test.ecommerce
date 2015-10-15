package home.vu.ecommerce.admin.service.impl;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.ItemResult;
import home.vu.ecommerce.admin.service.AdminItemService;
import home.vu.ecommerce.common.dao.ItemDao;
import home.vu.ecommerce.common.model.Item;

import java.util.List;

public class AdminItemServiceImpl implements AdminItemService {

    private ItemDao itemDao;

    // Constructor
    public AdminItemServiceImpl() {
    }

    // Setters
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.admin.service.AdminItemService#getAllItems(home.vu.ecommerce.admin.model.CountsSortingPagination)
     */
    public ItemResult getAllItems(CountsSortingPagination csp) {

        List<Item> items = itemDao.queryItems(csp.getSortItem(), csp.isSortAsc(), csp.getOffset(), csp.getRows());

        // 2) Retrieve count for pagination if applicable
        int count = csp.isDisplayCount() ? (int) itemDao.queryItems(null, true, 0, -1).size() : -1;
        csp.setCountAndComputePages(count);

        ItemResult result = new ItemResult();
        result.setItems(items);
        result.setCsp(csp);

        return result;
    }
}
