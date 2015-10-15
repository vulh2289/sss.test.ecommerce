package home.vu.ecommerce.admin.controller;

import home.vu.ecommerce.admin.constant.AdminConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Admin home controller.
 * 
 * @author Le Huy Vu
 */
@Controller
@RequestMapping(value = "/home")
public class AdminHomeController extends AbstractAdminController {

    @RequestMapping(method = RequestMethod.GET)
    public String cache() {

        return AdminConstants.VIEW_HOME;
    }

}
