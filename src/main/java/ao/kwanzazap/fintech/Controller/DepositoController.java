package ao.kwanzazap.fintech.Controller;

import ao.kwanzazap.fintech.Model.Conta;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepositoController {

    @RequestMapping("/deposito/{id}")

    public String  verdeposito(){
        return  "deposito";

    }



}
