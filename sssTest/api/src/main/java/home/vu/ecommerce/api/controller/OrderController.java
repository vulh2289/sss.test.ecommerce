package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.exception.SSSTestApiException;
import home.vu.ecommerce.api.model.BasketItem;
import home.vu.ecommerce.api.model.OrderInput;
import home.vu.ecommerce.api.service.OrderService;
import home.vu.ecommerce.common.enums.PaymentMethod;
import home.vu.ecommerce.common.model.OrderDetail;
import home.vu.ecommerce.common.model.User;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody String placeOrder(@RequestBody(required = true) OrderInput orderInput) {
        final User user = getCurrentUser();
        if (user == null) {
            throw new SSSTestApiException(String.format("Error retrieving info on current user"));
        }

        String redirectUrl = orderService.placeOrder(user, orderInput);

        return redirectUrl;
    }

    /**
     * complete an indirect order
     * 
     * @param orderInput
     * @return
     */
    @RequestMapping(value = "/completeIndirectOrder", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<String> placeOrder(@RequestParam String paymentMethod, @RequestBody Map<String, String> input) {
        final User user = getCurrentUser();
        if (user == null) {
            throw new SSSTestApiException(String.format("Error retrieving info on current user"));
        }

        boolean success = orderService.completeIndirectOrder(PaymentMethod.valueOf(paymentMethod), input);

        return new ResponseEntity<String>("{}", success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Check availability of a basket, then return items which are insufficient
     * 
     * @param basketItems
     * @return
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<List<BasketItem>> checkout(@RequestBody(required = true) List<BasketItem> basketItems) {
        final User user = getCurrentUser();
        if (user == null) {
            throw new SSSTestApiException(String.format("Error retrieving info on current user"));
        }

        List<BasketItem> insufficientItems = orderService.checkout(user, basketItems);

        return new ResponseEntity<List<BasketItem>>(insufficientItems, insufficientItems.size() == 0 ? HttpStatus.OK
                        : HttpStatus.BAD_REQUEST);
    }

    /**
     * place an order
     * 
     * @param orderInput
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<OrderDetail> getOrders() {
        final User user = getCurrentUser();
        if (user == null) {
            throw new SSSTestApiException(String.format("Error retrieving info on current user"));
        }

        return orderService.getOrderDetails(user);
    }
}
