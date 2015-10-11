package home.vu.ecommerce.common.security;

import home.vu.ecommerce.common.dao.UserDao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This is the glue between Spring and the User data layer.
 * 
 * @author Le Huy Vu
 *
 */
public class UserSecurityService implements UserDetailsService {

    // Constants

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final int IS_ADMIN = 1; // role column in User table

    private final UserDao userDao;

    /**
     * Constructor which takes a userDao.
     * 
     * @param userService
     */
    public UserSecurityService(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        home.vu.ecommerce.common.model.User user = null;
        try {
            user = userDao.getUser(userName);
        }
        catch (Exception e) {
            // note exception (debug) but continue on
        }

        // Check user
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userName);
        }
        else {
            String role = (user.getRole() == IS_ADMIN) ? ROLE_ADMIN : ROLE_USER;
            User clientUser = new User(userName, user.getSecret(), true, true, true, true, getAuthorities(role));
            return clientUser;
        }
    }

    // Private methods

    /**
     * Create collection set from ROLE_USER.
     * 
     * @param role
     * @return
     */
    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        return Collections.unmodifiableSet(authorities);
    }

}
