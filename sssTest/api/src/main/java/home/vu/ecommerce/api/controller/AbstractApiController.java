package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.service.ApiRequest;
import home.vu.ecommerce.common.constant.WebConstants;
import home.vu.ecommerce.common.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

/**
 * Base adoreboard API controller.
 * 
 * @author Le Huy Vu
 */
public abstract class AbstractApiController {

    // Protected fields

    @Autowired
    protected ApiRequest apiRequest;
    protected HttpHeaders httpHeadersPlainText = new HttpHeaders();

    /**
     * Constructor.
     */
    protected AbstractApiController() {
        this.httpHeadersPlainText.set(WebConstants.CONTENT_TYPE, WebConstants.TEXT_PLAIN_UTF8);
    }

    /**
     * Return the current user.
     * 
     * @return
     */
    protected User getCurrentUser() {
        return apiRequest != null ? apiRequest.getCurrentUser() : null;
    }
}