package home.vu.ecommerce.admin.controller;

import home.vu.ecommerce.admin.constant.AdminConstants;
import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.service.AdminUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Admin user controller.
 * 
 * @author Le Huy Vu
 */
@Controller
@RequestMapping(value = "/users")
public class AdminUsersController extends AbstractAdminController {

    private AdminUserService adminUserService;

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    public AdminUsersController() {
    }

    /**
     * Return a list of users.
     * 
     * @param model
     * @param action
     * @param id
     * @param filterLoginName
     * @param dc
     * @param sortItem
     * @param sortAsc
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model,
        @RequestParam(value = "action", required = false, defaultValue = "list") String action,
        @RequestParam(value = "id", required = false) String id,
        @RequestParam(value = "fId", required = false) String sFilterId,
        @RequestParam(value = "fLoginName", required = false) String filterLoginName,
        @RequestParam(value = "fActive", required = false) String sActive,
        @RequestParam(value = "dc", required = false, defaultValue = "true") String dc,
        @RequestParam(value = "sortItem", required = false, defaultValue = "id") String sortItem,
        @RequestParam(value = "sortAsc", required = false, defaultValue = "false") String sortAsc,
        @RequestParam(value = "page", required = false, defaultValue = "0") String page,
        @RequestParam(value = "rows", required = false, defaultValue = DEFAULT_ROWS) String rows) {

        CountsSortingPagination csp = getCountsSortingPagination(dc, sortItem, sortAsc, page, rows);

        // Service call
        model.addAttribute("result", this.adminUserService.getAllUsers(csp));

        return AdminConstants.VIEW_USERS_LIST;
    }

    // Private methods
}