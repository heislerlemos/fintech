package ao.kwanzazap.fintech.Controller;


import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Interface.MovimentoRepository;
import ao.kwanzazap.fintech.Model.Conta;
import ao.kwanzazap.fintech.Model.Movimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Optional;


@Controller

public class TransferenciaController {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private MovimentoRepository movimentoRepository;


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
        Movimento movimento = new Movimento();
        movimento.setBalanco(conta1.getBalanco());
        movimento.setMovimento(valor);
        movimento.setTipo("TRANSFERENCIA PARA CONTA:" + conta2.getNumero_de_conta());
        movimento.setData(new Date());
        movimento.setConta(conta1);
        movimento.setNumero_de_conta(conta1.getNumero_de_conta());
        movimentoRepository.save(movimento);

        contaRepository.save(conta2);
        Movimento movimento2 = new Movimento();
        movimento2.setBalanco(conta2.getBalanco());
        movimento2.setMovimento(valor);
        movimento2.setTipo("TRANSFERENCIA DA CONTA:" + conta1.getNumero_de_conta());
        movimento2.setData(new Date());
        movimento2.setConta(conta2);
        movimento2.setNumero_de_conta(conta2.getNumero_de_conta());
        movimentoRepository.save(movimento2);


        return "redirect:/";
    }


}

