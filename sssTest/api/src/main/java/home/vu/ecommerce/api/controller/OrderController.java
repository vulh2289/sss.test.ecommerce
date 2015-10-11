package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.exception.SSSTestApiException;
import home.vu.ecommerce.api.model.OrderInput;
import home.vu.ecommerce.api.service.OrderService;
import home.vu.ecommerce.common.model.User;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/orders")
public class OrderController extends AbstractApiController {

    private OrderService orderService;

    // Constructor
    public OrderController() {
    }

    // Setters
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * place an order
     * 
     * @param orderInput
     * @return
     */
    @RequestMapping(value = "/placeOrder", method = RequestMethod.PUT)
    public ResponseEntity<String> placeOrder(@RequestBody List<OrderInput> orderInput) {
        final User user = getCurrentUser();
        if (user == null) {
            throw new SSSTestApiException(String.format("Error retrieving info on current user"));
        }

        boolean success = orderService.placeOrder(user, orderInput);

        return new ResponseEntity<String>("{}", success ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
