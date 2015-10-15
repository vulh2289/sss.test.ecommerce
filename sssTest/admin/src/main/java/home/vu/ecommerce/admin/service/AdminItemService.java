package home.vu.ecommerce.admin.service;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.ItemResult;

public interface AdminItemService {

    /**
     * @param csp
     * @return
     */
    public ItemResult getAllItems(CountsSortingPagination csp);

}
