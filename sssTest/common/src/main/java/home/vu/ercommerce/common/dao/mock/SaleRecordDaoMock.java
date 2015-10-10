package home.vu.common.dao.mock;

import home.vu.common.dao.SaleRecordDao;
import home.vu.common.model.SaleRecord;
import home.vu.common.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Create mock data of sale records
 * 
 * @author Le Huy Vu
 *
 */
public class SaleRecordDaoMock implements SaleRecordDao {

    private List<SaleRecord> saleRecords;
    private int currentId = 0;

    /**
     * Create new empty list of sale records
     */
    public SaleRecordDaoMock() {
        saleRecords = new ArrayList<SaleRecord>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#createSaleRecord(home.vu.common.model.SaleRecord)
     */
    public void createSaleRecord(SaleRecord saleRecord) {
        saleRecord.setId(++currentId);
        saleRecords.add(saleRecord);
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.common.dao.SaleRecordDao#getSaleRecord(int)
     */
    public SaleRecord getSaleRecord(int id) {
        for (SaleRecord record : saleRecords) {
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
    public List<SaleRecord> getSaleRecords(User user) {
        List<SaleRecord> returnedSaleRecords = new ArrayList<SaleRecord>();
        for (SaleRecord record : saleRecords) {
            if (record.getBuyer().getId() == user.getId() || record.getBuyer().getUserName().equals(user.getUserName())) {
                returnedSaleRecords.add(record);
            }
        }

        return returnedSaleRecords;
    }

}
