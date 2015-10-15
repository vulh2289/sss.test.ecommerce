package home.vu.ecommerce.common.dao.mock.json;

import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.enums.ShipmentStatus;
import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.OrderDetail;
import home.vu.ecommerce.common.model.User;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class OrderDaoJsonMock extends AbstractJsonMockDao implements OrderDao {

    private int orderDetailsId;

    public OrderDaoJsonMock(String basePath, String jsonDbFile) {
        super(basePath, jsonDbFile);
    }

    /**
     * Update current id
     */
    public void updateCurrentId() {
        Order last = getLast(Order.class);

        currentId = last != null ? last.getId() : 0;
        for (OrderDetail details : last.getDetails()) {
            if (details.getId() > orderDetailsId) {
                orderDetailsId = details.getId();
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#createSaleRecord(home.vu.common.model.SaleRecord)
     */
    public void createOrder(Order saleRecord) {
        List<Order> allOrders = readDB(Order.class);
        saleRecord.setId(++currentId);

        for (OrderDetail details : saleRecord.getDetails()) {
            details.setId(++orderDetailsId);
        }

        allOrders.add(saleRecord);
        writeDB(allOrders);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#getSaleRecord(int)
     */
    public Order getOrder(int id) {
        List<Order> allOrders = readDB(Order.class);
        for (Order record : allOrders) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#getSaleRecords(home.vu.common.model.User)
     */
    public List<Order> getOrders(User user) {
        List<Order> allOrders = readDB(Order.class);
        List<Order> returningOrders = new ArrayList<Order>();
        for (Order record : allOrders) {
            if (user == null || record.getBuyer().getId() == user.getId() || record.getBuyer().getUserName().equals(user.getUserName())) {
                returningOrders.add(record);
            }
        }

        return returningOrders;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.dao.OrderDao#getOrderDetails(home.vu.ecommerce.common.model.User)
     */
    public List<OrderDetail> getOrderDetails(User user) {
        List<Order> allOrders = readDB(Order.class);
        List<OrderDetail> returningDetails = new ArrayList<OrderDetail>();
        for (Order record : allOrders) {
            if (user == null || (record.getBuyer().getId() == user.getId() || record.getBuyer().getUserName().equals(user.getUserName()))) {
                for (OrderDetail each : record.getDetails()) {
                    returningDetails.add(each);
                }
            }
        }

        return returningDetails;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.dao.OrderDao#updateShipmentStatus(int, home.vu.ecommerce.common.enums.ShipmentStatus)
     */
    public void updateShipmentStatus(int orderDetailsId, ShipmentStatus status) {
        List<Order> allOrders = readDB(Order.class);

        boolean found = false;
        for (Order order : allOrders) {
            for (OrderDetail details : order.getDetails()) {
                if (details.getId() == orderDetailsId) {
                    details.setShipmentStatus(status);
                    details.setUpdatedAt(new DateTime());
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (found) {
            writeDB(allOrders);
        }
        else {
            throw new RuntimeException("No such order!");
        }
    }
}
