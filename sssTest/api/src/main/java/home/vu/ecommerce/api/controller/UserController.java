package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.exception.SSSTestApiUserException;
import home.vu.ecommerce.common.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SSSTest REST API controller classes for Users.
 * 
 * @author Le Huy Vu
 */
@Controller
@RequestMapping(value = "/users")
public class UserController extends AbstractApiController {

    /**
     * Constructor.
     */
    public UserController() {
        super();
    }

    /**
     * Get details on a specific user (this is internal to us).
     * 
     * @return
     */
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody User getUser() {
        final User user = getCurrentUser();
        if (user == null) {
            throw new SSSTestApiUserException(String.format("Error retrieving info on current user"));
        }

        return user;
    }

}
