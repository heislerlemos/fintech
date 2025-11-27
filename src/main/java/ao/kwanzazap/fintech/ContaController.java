package ao.kwanzazap.fintech;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import ao.kwanzazap.fintech.Servicos;
import ao.kwanzazap.fintech.Movimentos;
import ao.kwanzazap.fintech.ContaRepository;
import ao.kwanzazap.fintech.Conta;

 @Controller

public class ContaController
{
    @Autowired
    private ContaRepository contaRepository;

    @ResponseBody
    @RequestMapping("/contas")
    public List<Conta> contar (){
        Conta conta = new Conta (192929, 2500,"Moises Baltazar", "AO0060002223339922",330022, Movimentos.DEPOSITO, Servicos.UNITEL );
        return Arrays.asList(conta, conta, conta, conta, conta);
    }



    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.POST)
    public void guardar (@RequestBody Conta conta) {
        contaRepository.save(conta);

    }
}
