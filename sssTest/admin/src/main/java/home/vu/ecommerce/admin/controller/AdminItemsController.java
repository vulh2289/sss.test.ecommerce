package home.vu.ecommerce.admin.controller;

import home.vu.ecommerce.admin.constant.AdminConstants;
import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.service.AdminItemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Admin item controller.
 * 
 * @author Le Huy Vu
 */
@Controller
@RequestMapping(value = "/items")
public class AdminItemsController extends AbstractAdminController {

    private AdminItemService adminItemService;

    public void setAdminItemService(AdminItemService adminItemService) {
        this.adminItemService = adminItemService;
    }

    public AdminItemsController() {
    }

    /**
     * Return a list of items.
     * 
     * @param model
     * @param action
     * @param dc
     * @param sortItem
     * @param sortAsc
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getItems(Model model,
        @RequestParam(value = "action", required = false, defaultValue = "list") String action,
        @RequestParam(value = "dc", required = false, defaultValue = "true") String dc,
        @RequestParam(value = "sortItem", required = false, defaultValue = "id") String sortItem,
        @RequestParam(value = "sortAsc", required = false, defaultValue = "false") String sortAsc,
        @RequestParam(value = "page", required = false, defaultValue = "0") String page,
        @RequestParam(value = "rows", required = false, defaultValue = DEFAULT_ROWS) String rows) {

        CountsSortingPagination csp = getCountsSortingPagination(dc, sortItem, sortAsc, page, rows);

        // Service call
        model.addAttribute("result", this.adminItemService.getAllItems(csp));

        return AdminConstants.VIEW_ITEMS_LIST;
    }

    // Private methods
}