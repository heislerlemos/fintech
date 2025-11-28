package ao.kwanzazap.fintech;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import ao.kwanzazap.fintech.Conta;


 @Controller

public class ContaController
{
    @Autowired
    private ContaRepository contaRepository;

    @ResponseBody
    @RequestMapping("/contas")
    public List<Conta> contar (){
        return contaRepository.findAll((Sort.by(Sort.Direction.DESC, "id")));

    }



    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.POST)
    public void guardar (@RequestBody Conta conta) {
        contaRepository.save(conta);

    }

    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.PUT)
    public void atualizar (@RequestBody Conta conta){
        contaRepository.save(conta);
    }
}
