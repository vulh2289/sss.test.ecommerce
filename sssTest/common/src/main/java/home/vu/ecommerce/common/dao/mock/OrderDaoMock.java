package home.vu.ecommerce.common.dao.mock;

import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.model.Order;
import home.vu.ecommerce.common.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Create mock data of sale records
 * 
 * @author Le Huy Vu
 *
 */
public class OrderDaoMock implements OrderDao {

    private List<Order> allOrders;
    private int currentId = 0;

    /**
     * Create new empty list of sale records
     */
    public OrderDaoMock() {
        allOrders = new ArrayList<Order>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#createSaleRecord(home.vu.common.model.SaleRecord)
     */
    public void createOrder(Order saleRecord) {
        saleRecord.setId(++currentId);
        allOrders.add(saleRecord);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#getSaleRecord(int)
     */
    public Order getOrder(int id) {
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
        List<Order> returnedSaleRecords = new ArrayList<Order>();
        for (Order record : allOrders) {
            if (record.getBuyer().getId() == user.getId() || record.getBuyer().getUserName().equals(user.getUserName())) {
                returnedSaleRecords.add(record);
            }
        }

        return returnedSaleRecords;
    }

}
