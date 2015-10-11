package home.vu.ercommerce.common.dao.mock;

import home.vu.ecommerce.common.dao.SaleRecordDao;
import home.vu.ecommerce.common.dao.mock.SaleRecordDaoMock;

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
    private SaleRecordDao saleRecordDao;

    @Before
    public void setup() throws Exception {
        saleRecordDao = new SaleRecordDaoMock();
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
