package home.vu.ecommerce.admin.controller;

import static java.util.Arrays.asList;
import home.vu.ecommerce.admin.model.CountsSortingPagination;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.ui.Model;

/**
 * Abstract Admin Controller which provides Pagination feature
 * 
 * @author Le Huy Vu
 *
 */
public class AbstractAdminController {

    protected static final String DEFAULT_ROWS = "25";

    protected static final DateTimeFormatter DTF_DATE_TIME = DateTimeFormat.forPattern("dd/MM/yyyy - HH:mm");

    /**
     * Return a counts / sorting / pagination object from HTTP strings.
     * 
     * @param dc
     * @param sortItem
     * @param sortAsc
     * @param page
     * @param rows
     * @return
     */
    protected CountsSortingPagination getCountsSortingPagination(String dc, String sortItem, String sortAsc, String page, String rows) {
        CountsSortingPagination csp = new CountsSortingPagination();

        csp.setSortAsc("true".equals(sortAsc));
        csp.setSortItem(sortItem);
        csp.setDisplayCount("true".equals(dc));
        csp.setPage(page != null ? Integer.parseInt(page) : -1);
        csp.setRows(rows != null ? Integer.parseInt(rows) : -1);

        return csp;
    }

    /**
     * Supporting method to display message
     * 
     * @param model
     * @param success
     * @param successMsg
     * @param errorMsg
     */
    protected void message(Model model, boolean success, String successMsg, String errorMsg) {
        if (success) {
            model.addAttribute("success", successMsg);
        }
        else {
            model.addAttribute("errors", asList(errorMsg));
        }
    }
}
