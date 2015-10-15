package home.vu.ecommerce.api.service;

import home.vu.ecommerce.common.enums.PaymentMethod;

import java.util.Map;

public interface PaymentService {

    /**
     * Make a payment, and return a url if any
     * 
     * @param method
     * @param directPayment
     * @param total
     * @param currency
     * @param customInput
     * @return
     */
    public String makePayment(PaymentMethod method, boolean directPayment, float total, String currency, Map<String, String> customInput);

    /**
     * Complete indirect payment, details will be specified in customInput
     * 
     * @param paymentMethod
     * @param customInput
     * @return
     */
    public boolean completeIndirectPayment(PaymentMethod paymentMethod, Map<String, String> customInput);
}
