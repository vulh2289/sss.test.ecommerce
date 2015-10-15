package home.vu.ecommerce.api.aop.advice;

import home.vu.ecommerce.api.exception.SSSTestApiException;
import home.vu.ecommerce.common.constant.WebConstants;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception advice class for controller exceptions
 * 
 * @author Le Huy Vu
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    // Constants
    private final HttpHeaders httpHeaders;

    /**
     * Constructor which sets up the httpHeaders used in the ResponseEntity returned when handling exceptions.
     */
    public ExceptionControllerAdvice() {
        httpHeaders = getHeaders(WebConstants.TEXT_PLAIN_UTF8);
    }

    /**
     * If a general Exception occurs then return a HTTP internal server error (500).
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<String>("General exception has occured: " + e.getMessage(), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle validation errors and return a HTTP 'bad request' error (400) indicating a user error.
     * 
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> invalidException(MethodArgumentNotValidException e) {

        StringBuilder sb = new StringBuilder();
        BindingResult result = e.getBindingResult();
        for (FieldError error : result.getFieldErrors()) {
            sb.append("\"" + error.getField() + "\" (\"" + error.getRejectedValue() + "\") ");
        }

        return new ResponseEntity<String>("Invalid parameters: " + sb.toString(), httpHeaders, HttpStatus.BAD_REQUEST);
    }

    /**
     * If a SSSTestApiException occurs then return a HTTP 'bad request' error (400).
     * 
     * @param apiUserException
     * @return
     */
    @ExceptionHandler(SSSTestApiException.class)
    public ResponseEntity<String> apiUserException(SSSTestApiException e) {
        return new ResponseEntity<String>(e.getMessage(), httpHeaders, HttpStatus.BAD_REQUEST);
    }

    // Private methods

    /**
     * Return HttpHeaders.
     * 
     * @param contentType
     * @return
     */
    private HttpHeaders getHeaders(String contentType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(WebConstants.CONTENT_TYPE, WebConstants.TEXT_PLAIN_UTF8);
        return httpHeaders;
    }
}
