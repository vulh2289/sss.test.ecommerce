package home.vu.ecommerce.admin.service.impl;

import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.model.result.UserResult;
import home.vu.ecommerce.admin.service.AdminUserService;
import home.vu.ecommerce.common.dao.UserDao;

public class AdminUserServiceImpl implements AdminUserService {

    private UserDao userDao;

    // Empty Constructor
    public AdminUserServiceImpl() {
    }

    // Setters
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.admin.service.AdminUserService#getAllUsers(home.vu.ecommerce.admin.model.CountsSortingPagination)
     */
    public UserResult getAllUsers(CountsSortingPagination csp) {
        UserResult result = new UserResult();
        result.setUsers(userDao.getUsers(false));
        return result;
    }

}
