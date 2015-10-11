package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.service.InventoryService;
import home.vu.ecommerce.common.model.Item;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController extends AbstractApiController {

    private InventoryService inventoryService;

    // Constructor
    public InventoryController() {
    }

    // Setters
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * @param sortBy
     * @param asc
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Item> getInventory(@RequestParam String sortBy,
        @RequestParam boolean asc,
        @RequestParam int offset,
        @RequestParam int limit) {

        return inventoryService.getItems(sortBy, asc, offset, limit);
    }

}
