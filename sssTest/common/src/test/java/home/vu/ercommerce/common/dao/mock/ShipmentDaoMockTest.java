package home.vu.ercommerce.common.dao.mock;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import home.vu.ecommerce.common.dao.ShipmentDao;
import home.vu.ecommerce.common.dao.mock.ShipmentDaoMock;
import home.vu.ecommerce.common.enums.ShipmentStatus;
import home.vu.ecommerce.common.model.Item;
import home.vu.ecommerce.common.model.SaleRecord;
import home.vu.ecommerce.common.model.Shipment;
import home.vu.ecommerce.common.model.User;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ShipmentDaoMockTest {

    private static final String NEW_USER = "new user";
    private ShipmentDao shipmentDao;

    @Before
    public void setup() throws Exception {
        shipmentDao = new ShipmentDaoMock();
    }

    @Test
    public void shouldCreateNewShipment() throws Exception {

        // Given
        User user = new User();
        user.setUserName(NEW_USER);

        Item item = new Item("Item1", 1.5f, 10, true);

        SaleRecord record1 = new SaleRecord(user, item);

        Shipment shipment = new Shipment(asList(record1), ShipmentStatus.DISPATCHED);
        List<Shipment> shipments = shipmentDao.queryShipments("created_at", true, 0, 100);
        assertEquals(0, shipments.size());

        // When
        shipmentDao.createShipment(shipment);

        // Then
        shipments = shipmentDao.queryShipments("created_at", true, 0, 100);
        assertEquals(1, shipments.size());
    }

    @Test
    public void shouldUpdateShipmentStatus() throws Exception {

        // Given
        User user = new User();
        user.setUserName(NEW_USER);

        Item item = new Item("Item1", 1.5f, 10, true);

        SaleRecord record1 = new SaleRecord(user, item);

        Shipment shipment = new Shipment(asList(record1), ShipmentStatus.DISPATCHED);
        shipmentDao.createShipment(shipment);
        List<Shipment> shipments = shipmentDao.queryShipments("created_at", true, 0, 100);
        assertEquals(1, shipments.size());

        // When
        shipmentDao.updateShipmentStatus(1, ShipmentStatus.DELIVERED);

        // Then
        shipments = shipmentDao.queryShipments("created_at", true, 0, 100);
        assertEquals(1, shipments.size());
        assertEquals(ShipmentStatus.DELIVERED, shipments.get(0).getShipmentStatus());
    }
}
