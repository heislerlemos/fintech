package ao.kwanzazap.fintech.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class TransferenciaController {


    @GetMapping("/tranferencia")
    public String transferencia (){
        return "transferencia";
    }
}
