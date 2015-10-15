package home.vu.ecommerce.admin.controller;

import home.vu.ecommerce.admin.constant.AdminConstants;
import home.vu.ecommerce.admin.model.CountsSortingPagination;
import home.vu.ecommerce.admin.service.AdminOrderService;
import home.vu.ecommerce.common.enums.ShipmentStatus;

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
@RequestMapping(value = "/orders")
public class AdminOrdersController extends AbstractAdminController {

    private AdminOrderService adminOrderService;

    public void setAdminOrderService(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    public AdminOrdersController() {
    }

    /**
     * Return a list of users.
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
    public String getOrders(Model model,
        @RequestParam(value = "action", required = false, defaultValue = "list") String action,
        @RequestParam(value = "orderId", required = false) String orderId,
        @RequestParam(value = "orderDetailsId", required = false) String orderDetailsId,
        @RequestParam(value = "dc", required = false, defaultValue = "true") String dc,
        @RequestParam(value = "sortItem", required = false, defaultValue = "id") String sortItem,
        @RequestParam(value = "sortAsc", required = false, defaultValue = "false") String sortAsc,
        @RequestParam(value = "page", required = false, defaultValue = "0") String page,
        @RequestParam(value = "rows", required = false, defaultValue = DEFAULT_ROWS) String rows) {

        CountsSortingPagination csp = getCountsSortingPagination(dc, sortItem, sortAsc, page, rows);

        if (action.equalsIgnoreCase(AdminConstants.ACTION_DISPATCH) && orderDetailsId != null) {
            dispatch(orderId, orderDetailsId);
        }

        // Service call
        model.addAttribute("result", this.adminOrderService.getOrders(csp));

        return AdminConstants.VIEW_ORDERS_LIST;
    }

    // Private methods
    private void dispatch(String orderId, String orderDetailsId) {
        int orderDetailsIdInt = Integer.parseInt(orderDetailsId);
        int orderIdInt = Integer.parseInt(orderId);
        this.adminOrderService.changeShipmentStatus(orderIdInt, orderDetailsIdInt, ShipmentStatus.DISPATCHED);
    }
}