package home.vu.ecommerce.common.payment.impl;

import home.vu.ecommerce.common.exception.SSSTestPaymentException;
import home.vu.ecommerce.common.model.PaypalExecutionInput;
import home.vu.ecommerce.common.payment.PaymentSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Implementation of a PaymentSystem interface using Paypal and it's SDK.
 * 
 * ref: https://devtools-paypal.com/guide/pay_paypal
 * 
 * @author Le Huy Vu
 *
 */
public class PaymentSystemPaypal implements PaymentSystem {

    // Fields
    private String appId;
    private String secret;
    private Map<String, String> sdkConfiguration;

    // Injtected fields
    private String returnUrl;
    private String cancelUrl;

    // Constructor
    public PaymentSystemPaypal(String appId, String secret, Map<String, String> configuration) {
        this.appId = appId;
        this.secret = secret;
        this.sdkConfiguration = configuration;
    }

    // Setters
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.payment.PaymentSystem#directPayment(java.lang.String, float, java.lang.String, java.util.Map)
     */
    public boolean directPayment(String description, float total, String currency, Map<String, String> customInput) {
        // NOTE: Not implementing direct payment

        throw new SSSTestPaymentException("Direct payment has not been implemented");
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.payment.PaymentSystem#indirectPayment(java.lang.String, float, java.lang.String, java.util.Map)
     */
    public String indirectPayment(String description, float total, String currency, Map<String, String> customInput) {

        try {
            String accessToken = new OAuthTokenCredential(appId, secret, sdkConfiguration).getAccessToken();
            APIContext apiContext = new APIContext(accessToken);
            apiContext.setConfigurationMap(sdkConfiguration);

            Amount amount = new Amount();
            amount.setCurrency(currency);
            amount.setTotal(String.format("%.2f", total)); // TODO: what is happening here?
            // amount.setTotal("12");
            Transaction transaction = new Transaction();
            transaction.setDescription(description);
            transaction.setAmount(amount);

            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);

            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);
            RedirectUrls redirectUrls = new RedirectUrls();
            // redirectUrls.setCancelUrl("http://localhost:8080/sssTest-ui/");
            // redirectUrls.setReturnUrl("http://localhost:8080/sssTest-ui/");
            redirectUrls.setCancelUrl(cancelUrl);
            redirectUrls.setReturnUrl(returnUrl); // Replace with this, whenever we have the link ready
            payment.setRedirectUrls(redirectUrls);

            Payment createdPayment = payment.create(apiContext);

            List<Links> links = createdPayment.getLinks();
            for (Links link : links) {
                if (link.getRel().equals("approval_url")) {
                    return link.getHref();
                }
            }
        }
        catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new SSSTestPaymentException("Error while handling payment using Paypal");
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see home.vu.ecommerce.common.payment.PaymentSystem#executeIndirectPayment(java.util.Map)
     */
    public boolean executeIndirectPayment(Map<String, String> customInput) {
        try {
            String accessToken = new OAuthTokenCredential(appId, secret, sdkConfiguration).getAccessToken();
            APIContext apiContext = new APIContext(accessToken);
            apiContext.setConfigurationMap(sdkConfiguration);

            PaypalExecutionInput input = new PaypalExecutionInput();
            input.setPaymentId(customInput.get("paymentId"));
            input.setPayerId(customInput.get("payerId"));

            Payment payment = new Payment(input.getPaymentId(), null);
            payment.setId(input.getPaymentId());
            PaymentExecution paymentExecute = new PaymentExecution();
            paymentExecute.setPayerId(input.getPayerId());
            payment.execute(apiContext, paymentExecute);

            return true;
        }
        catch (ClassCastException e) {
            throw new SSSTestPaymentException("Invalid paypal execution details");
        }
        catch (PayPalRESTException e) {
            throw new SSSTestPaymentException("Error while trying to execute paypal payment");
        }
    }
}
