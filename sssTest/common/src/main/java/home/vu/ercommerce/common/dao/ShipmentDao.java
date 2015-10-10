package home.vu.common.dao;

import home.vu.common.enums.ShipmentStatus;
import home.vu.common.model.Shipment;

import java.util.List;

/**
 * Interface that provides Shipment CRUD communication to database.
 * 
 * @author Le Huy Vu
 *
 */
public interface ShipmentDao {

    /**
     * Create new shipment
     * 
     * @param shipment
     */
    public void createShipment(Shipment shipment);

    /**
     * update a shipments' status
     * 
     * @param id
     * @param status
     */
    public void updateShipmentStatus(int id, ShipmentStatus status);

    /**
     * get all shipments with specified values
     * 
     * @param sortBy
     * @param asc
     * @param offset
     * @param limit
     * @return
     */
    public List<Shipment> queryShipments(String sortBy, boolean asc, int offset, int limit);
}
