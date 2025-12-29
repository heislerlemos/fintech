package ao.kwanzazap.fintech.Controller;


import ao.kwanzazap.fintech.Service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/send")
    public String sendWhatsAppMessage(@RequestParam("to") String to,
                                      @RequestParam("message") String message) {
        return twilioService.sendWhatsAppMessage(to, message);
    }
}