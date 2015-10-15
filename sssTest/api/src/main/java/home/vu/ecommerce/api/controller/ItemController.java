package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.service.InventoryService;
import home.vu.ecommerce.common.model.Item;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Provide controller to manage inventory
 * 
 * @author Le Huy Vu
 *
 */
@Controller
@RequestMapping(value = "/items")
public class ItemController extends AbstractApiController {

    private InventoryService inventoryService;

    // Constructor
    public ItemController() {
    }

    // Setters
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Get all items with specfified following
     * 
     * @param sortBy
     * @param asc
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Item> getItems(@RequestParam String sortBy,
        @RequestParam boolean asc,
        @RequestParam int offset,
        @RequestParam int limit) {

        return inventoryService.getItems(sortBy, asc, offset, limit);
    }

    /**
     * @param sortBy
     * @param asc
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Item getItemDetails(@PathVariable int id) {

        return inventoryService.getItem(id);
    }

}
