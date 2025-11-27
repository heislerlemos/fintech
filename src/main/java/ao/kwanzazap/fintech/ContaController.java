package ao.kwanzazap.fintech;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


 @Controller

public class ContaController
{

    @ResponseBody
    @RequestMapping("/contas")
    public List<Conta> contar (){
        Conta conta = new Conta (192929, 2500,"Moises Baltazar", "AO0060002223339922",330022, Movimentos.DEPOSITO, Servicos.UNITEL );
        return Arrays.asList(conta, conta, conta, conta, conta);
    }
}
