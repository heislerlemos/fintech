package ao.kwanzazap.fintech.Controller;

import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Model.Conta;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepositoController {
    @Autowired
    private ContaRepository contaRepository;
/*
    @RequestMapping("/deposito/{id}")

    public String  verdeposito(Model model , @RequestParam long id){
        Conta conta = contaRepository.findById(id).orElse(null);

        Conta conta1 = new Conta();
        conta1.setBalanco(conta.getBalanco());

        model.addAttribute("conta", conta);
        model.addAttribute("conta1", conta1);

        return  "deposito";

    }

*/


    @RequestMapping("/deposito/{id}")

    public String  verdeposito(){
        return  "deposito";

    }
}
