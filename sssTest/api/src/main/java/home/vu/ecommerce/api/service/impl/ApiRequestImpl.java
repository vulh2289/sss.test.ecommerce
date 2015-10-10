package home.vu.ecommerce.api.service.impl;

import home.vu.ecommerce.api.service.ApiRequest;
import home.vu.ercommerce.common.dao.UserDao;
import home.vu.ercommerce.common.enums.UserRole;
import home.vu.ercommerce.common.model.User;

import java.io.Serializable;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApiRequestImpl implements ApiRequest, Serializable {

    private static final long serialVersionUID = 2328884957969133427L;

    // Fields
    private final Authentication authentication;
    // private HttpServletRequest request;
    private User user;

    /**
     * Constructor which takes a UserMapper object.
     * 
     * @param userMapper
     */
    public ApiRequestImpl(UserDao userDao) {
        this.authentication = SecurityContextHolder.getContext().getAuthentication();

        // Set user
        String username = authentication.getName();
        if (username != null) {
            this.user = userDao.getUser(username);
            Iterator<? extends GrantedAuthority> authorities = this.authentication.getAuthorities().iterator();
            if (authorities.hasNext()) {
                UserRole role = UserRole.getType(authorities.next().getAuthority());
                this.user.setRole(role != null ? role.ordinal() : UserRole.ROLE_USER.ordinal());
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.adoreboard.api.service.ApiRequest#getCurrentUser()
     */
    public User getCurrentUser() {
        return user;
    }
}
