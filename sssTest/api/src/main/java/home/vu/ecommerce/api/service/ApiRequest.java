package home.vu.ecommerce.api.service;

import home.vu.ercommerce.common.model.User;

/**
 * Represents an individual API request, and holds information common to all requests.
 * 
 * @author Le Huy Vu
 *
 */
public interface ApiRequest {

    /**
     * Return the current user.
     * 
     * @return
     */
    public User getCurrentUser();

}