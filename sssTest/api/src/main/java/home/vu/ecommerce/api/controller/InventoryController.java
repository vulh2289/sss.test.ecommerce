package home.vu.ecommerce.api.controller;

import home.vu.ecommerce.api.service.InventoryService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

    private InventoryService intentoryService;

    // Constructor
    public InventoryController() {
    }

    // Setters
    public void setIntentoryService(InventoryService intentoryService) {
        this.intentoryService = intentoryService;
    }

}
