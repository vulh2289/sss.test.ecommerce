package home.vu.ecommerce.api.service.impl;

import home.vu.ecommerce.api.exception.SSSTestApiException;
import home.vu.ecommerce.api.service.PaymentService;
import home.vu.ecommerce.common.enums.PaymentMethod;
import home.vu.ecommerce.common.payment.PaymentSystem;

import java.util.Map;

public class PaymentServiceImpl implements PaymentService {

    private Map<String, PaymentSystem> listOfPaymentSystems;

    // Constructor
    public PaymentServiceImpl() {

    }

    // Setters
    public void setListOfPaymentSystems(Map<String, PaymentSystem> listOfPaymentSystems) {
        this.listOfPaymentSystems = listOfPaymentSystems;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.PaymentService#makePayment(home.vu.ecommerce.common.enums.PaymentMethod, boolean, float, java.lang.String, java.util.Map)
     */
    public String makePayment(PaymentMethod method, boolean directPayment, float total, String currency, Map<String, String> customInput) {

        PaymentSystem paymentSystem = listOfPaymentSystems.get(method.toString());
        if (paymentSystem == null) {
            throw new SSSTestApiException("No such payment method: [" + method + "]");
        }

        if (directPayment) {
            paymentSystem.directPayment("SSSTest transaction", total, currency, customInput);
        }
        else {
            return paymentSystem.indirectPayment("SSSTest transaction", total, currency, customInput);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.api.service.PaymentService#completeIndirectPayment(home.vu.ecommerce.common.enums.PaymentMethod, java.util.Map)
     */
    public boolean completeIndirectPayment(PaymentMethod method, Map<String, String> customInput) {
        PaymentSystem paymentSystem = listOfPaymentSystems.get(method.toString());
        if (paymentSystem == null) {
            throw new SSSTestApiException("No such payment method: [" + method + "]");
        }

        return paymentSystem.executeIndirectPayment(customInput);
    }
}
