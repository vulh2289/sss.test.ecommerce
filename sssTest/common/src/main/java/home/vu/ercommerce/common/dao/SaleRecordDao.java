package home.vu.ercommerce.common.dao;

import home.vu.ercommerce.common.model.SaleRecord;
import home.vu.ercommerce.common.model.User;

import java.util.List;

/**
 * Interface that provide SaleRecord CRUD calls to the database
 * 
 * @author Le Huy Vu
 *
 */
public interface SaleRecordDao {

    /**
     * Create a sale record
     * 
     * @param newUser
     */
    public void createSaleRecord(SaleRecord saleRecord);

    /**
     * Get a saleRecord which has specified id
     * 
     * @param id
     * @return
     */
    public SaleRecord getSaleRecord(int id);

    /**
     * Get all sale record of a users.
     * 
     * Leave user null to get all records
     * 
     * @param activeOnly
     * @return
     */
    public List<SaleRecord> getSaleRecords(User user);
}
