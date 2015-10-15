package home.vu.ecommerce.admin.service;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.UserResult;

public interface AdminUserService {

    /**
     * Get all users
     * 
     * @param csp
     * 
     * @return
     */
    public UserResult getAllUsers(CountsSortingPagination csp);

}
