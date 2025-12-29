package ao.kwanzazap.fintech.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class TwilioService {
    /*
      @Value("${twilio.account.sid}")
      private String accountSid;

      @Value("${twilio.auth.token}")
      private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String fromWhatsAppNumber;
  */
    public TwilioService() {
        String  accountSid = "${twilio.account.sid}";
        String  authToken = "${twilio.auth.token}";

        //  Twilio.init(accountSid,authToken);
        Twilio.init(accountSid,authToken);
    }

    public String sendWhatsAppMessage(String to, String messageBody) {
        String fromWhatsAppNumber = "${twilio.whatsapp.number}";

        Message message = Message.creator(
                        new PhoneNumber("whatsapp:" + to),
                        new PhoneNumber(fromWhatsAppNumber),
                        messageBody)
                .create();

        return message.getSid();
    }
}
