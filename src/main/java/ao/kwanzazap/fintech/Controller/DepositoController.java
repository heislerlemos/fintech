package ao.kwanzazap.fintech.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepositoController {

    @RequestMapping("/deposito/{id}")

    public String  verdeposito(){
        return  "deposito";

    }
}
