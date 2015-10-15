import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class paypalTest {

    public static void main(String [] args) {
        Map<String, String> sdkConfig = new HashMap<String, String>();
        sdkConfig.put("mode", "sandbox");
        sdkConfig.put("http.ConnectionTimeOut", "5000");
        sdkConfig.put("http.Retry", "1");
        sdkConfig.put("http.ReadTimeOut", "30000");
        sdkConfig.put("http.MaxConnection", "100");
        sdkConfig.put("service.EndPoint", "https://api.sandbox.paypal.com");
        sdkConfig.put("http.ProxyPort", "8080");
        sdkConfig.put("http.ProxyHost", "127.0.01");
        sdkConfig.put("http.UseProxy", "false");

        try {
            String accessToken = new OAuthTokenCredential("AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS", "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL", sdkConfig)
                            .getAccessToken();

            APIContext apiContext = new APIContext(accessToken);
            apiContext.setConfigurationMap(sdkConfig);

            Amount amount = new Amount();
            amount.setCurrency("USD");
            amount.setTotal("12");

            Transaction transaction = new Transaction();
            transaction.setDescription("creating a payment");
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
            redirectUrls.setCancelUrl("https://devtools-paypal.com/guide/pay_paypal?cancel=true");
            redirectUrls.setReturnUrl("https://devtools-paypal.com/guide/pay_paypal?success=true");
            payment.setRedirectUrls(redirectUrls);

            Payment created = payment.create(apiContext);

            List<Links> links = created.getLinks();
            for (Links link : links) {
                if (link.getRel().equals("approval_url")) {
                    System.out.println(link.getHref());
                }
            }

        }
        catch (PayPalRESTException e) {
            e.printStackTrace();
        }
    }
}
