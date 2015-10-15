package home.vu.ecommerce.common.payment;

import java.util.Map;

public interface PaymentSystem {

    /**
     * Directly handle payment of a user in our system/
     * 
     * @param description
     * @param total
     * @param currency
     * @param customInput
     *            : will contain payment details
     */
    public boolean directPayment(String description, float total, String currency, Map<String, String> customInput);

    /**
     * Not handling payment in our system, rather we ask third party to handle and return redirect_url.
     * 
     * 
     * @param description
     * @param total
     * @param currency
     * @param customInput
     *            : will contain payment details
     * @return
     */
    public String indirectPayment(String description, float total, String currency, Map<String, String> customInput);

    /**
     * Execute pending payment
     * 
     * @param customInput
     *            : will contain payment details
     * @return
     */
    public boolean executeIndirectPayment(Map<String, String> customInput);
}
