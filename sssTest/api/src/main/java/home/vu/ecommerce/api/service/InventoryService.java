package home.vu.ecommerce.api.service;

import home.vu.ecommerce.common.model.Item;

import java.util.List;

public interface InventoryService {

    List<Item> getItems(String sortBy, boolean asc, int offset, int limit);

}
