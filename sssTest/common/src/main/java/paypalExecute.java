import java.util.HashMap;
import java.util.Map;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class paypalExecute {

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
            String accessToken = "Bearer A101.yh07YSPuhLH8QPHT5U77cYDhSbaXme2yY-x6sEzeYlKKWvGL5j55dHa689dfADpd.0I1eKYiL5XJW0p0mHr8CORwjj78";
            APIContext apiContext = new APIContext(accessToken);
            apiContext.setConfigurationMap(sdkConfig);

            Payment payment = new Payment("PAY-2P349002PY6677649KYOVS6A", null);
            PaymentExecution paymentExecute = new PaymentExecution();
            payment.setId("PAY-2P349002PY6677649KYOVS6A");
            paymentExecute.setPayerId("29N3K4UBEPDTA");
            payment.execute(apiContext, paymentExecute);

            System.out.println("ASd");
        }
        catch (PayPalRESTException e) {
            e.printStackTrace();
        }

    }
}
