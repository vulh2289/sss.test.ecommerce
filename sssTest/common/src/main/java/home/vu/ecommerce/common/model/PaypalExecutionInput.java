package home.vu.ecommerce.common.model;

public class PaypalExecutionInput {

    private String paymentId;
    private String payerId;

    // Empty Constructor
    public PaypalExecutionInput() {
    }

    // getters & setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

}
