package home.vu.common.dao.mock;

import home.vu.common.dao.ShipmentDao;
import home.vu.common.enums.ShipmentStatus;
import home.vu.common.model.Shipment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShipmentDaoMock implements ShipmentDao {

    private List<Shipment> shipments;
    private int currentId = 0;

    public ShipmentDaoMock() {
        shipments = new ArrayList<Shipment>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ShipmentDao#createShipment(home.vu.common.model.Shipment)
     */
    public void createShipment(Shipment shipment) {
        shipment.setId(++currentId);
        shipments.add(shipment);

    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ShipmentDao#updateShipmentStatus(int, home.vu.common.enums.ShipmentStatus)
     */
    public void updateShipmentStatus(int id, ShipmentStatus status) {
        for (Shipment shipment : shipments) {
            if (shipment.getId() == id) {
                shipment.setShipmentStatus(status);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.ShipmentDao#queryShipments(java.lang.String, boolean, int, int)
     */
    public List<Shipment> queryShipments(String sortBy, boolean asc, int offset, int limit) {
        if (limit == -1 || limit > shipments.size()) {
            limit = shipments.size();
        }

        if (sortBy != null) {
            Comparator<Shipment> customComparator = new CustomShipmentComparator(Comparable.valueOf(sortBy), asc);
            Collections.sort(shipments, customComparator);
        }

        List<Shipment> returnedList = new ArrayList<Shipment>();
        for (int i = 0; i < limit; i++) {
            returnedList.add(shipments.get(offset + i));
        }
        return returnedList;
    }

    /**
     * Custom Comparator for Item object
     * 
     * @author Le Huy Vu
     *
     */
    private class CustomShipmentComparator implements Comparator<Shipment> {

        private Comparable comparable;
        private boolean asc;

        public CustomShipmentComparator(Comparable comparable, boolean asc) {
            this.comparable = comparable;
            this.asc = asc;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Shipment i1, Shipment i2) {
            switch (this.comparable) {
                case shipment_status:
                    return asc ? i1.getShipmentStatus().compareTo(i2.getShipmentStatus()) : i2.getShipmentStatus().compareTo(
                        i1.getShipmentStatus());
                case created_at:
                    return asc ? i1.getCreatedAt().compareTo(i2.getCreatedAt()) : i2.getCreatedAt().compareTo(i1.getCreatedAt());
                case updated_at:
                    return asc ? i1.getUpdatedAt().compareTo(i2.getUpdatedAt()) : i2.getUpdatedAt().compareTo(i1.getUpdatedAt());
            }
            return 0;

        }
    }

    /**
     * Sorting case
     * 
     * @author Le Huy Vu
     *
     */
    private enum Comparable {
        shipment_status, created_at, updated_at;
    }
}
