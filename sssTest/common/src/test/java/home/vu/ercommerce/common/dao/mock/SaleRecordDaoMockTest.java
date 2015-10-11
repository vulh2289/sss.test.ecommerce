package home.vu.ercommerce.common.dao.mock;

import home.vu.ecommerce.common.dao.OrderDao;
import home.vu.ecommerce.common.dao.mock.OrderDaoMock;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit testing for SaleRecordDaoMock
 * 
 * @author Le Huy Vu
 *
 */
public class SaleRecordDaoMockTest {

    // Fields
    private OrderDao saleRecordDao;

    @Before
    public void setup() throws Exception {
        saleRecordDao = new OrderDaoMock();
    }

    @Test
    public void shouldCreateSaleRecord() throws Exception {

    }

    @Test
    public void shouldGetSaleRecords() throws Exception {

    }

    @Test
    public void shouldGetSaleRecord() throws Exception {

    }
}
