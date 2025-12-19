package ao.kwanzazap.fintech.Controller;


import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;


@Controller

public class TransferenciaController {
    @Autowired
    private ContaRepository contaRepository;


    public Optional<Conta> getConta (Long id ) {
        return contaRepository.findById(id);
    }

    @GetMapping("/tranferencia")
    public String transferencia (){
        return "transferencia";
    }
    @RequestMapping(path = "/contas/transferencia"  , method = RequestMethod.POST)

    public String transferencia (Long id1 , double valor , Long id2) {
        Conta conta1 = getConta(id1).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        Conta conta2 = getConta(id2).orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (conta1.getBalanco() < valor ){
            throw new RuntimeException("Valores insuficientes para fazer transferencia");

        }

        conta1.setBalanco(conta1.getBalanco() - valor );
        conta2.setBalanco(conta2.getBalanco() + valor);

        contaRepository.save(conta1);
        contaRepository.save(conta2);

        return "redirect:/";
    }


}

