package ao.kwanzazap.fintech.Controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ao.kwanzazap.fintech.Service.ContaServico;
import ao.kwanzazap.fintech.Model.Conta;
import ao.kwanzazap.fintech.Interface.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller

public class ContaController
{
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaServico contaServico;

    // HTML TEMPLATE VIEW
    @GetMapping("/")
    public String contas(Model model){
        model.addAttribute("contas", contaRepository.findAll((Sort.by(Sort.Direction.DESC, "id"))));
        return "index";
    }

    // JSON VIEW
    @ResponseBody
    @RequestMapping("/contas")
    public List<Conta> contas (){
        return contaRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/contas/{id}")
    public Conta getAccount(@PathVariable Long id) {
        return contaServico.getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }


    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.POST)
    public void guardar (@RequestBody Conta conta) {
        contaRepository.save(conta);

    }

    @ResponseBody
    @PostMapping("/contas/{id}/deposito")
    public Conta deposito (@PathVariable Long id, @RequestBody Map<String,Long> request) {
        Long valor = request.get("valor");
        return contaServico.deposito(id,valor);
    }

    public Optional<Conta> getConta (Long id ) {
        return contaRepository.findById(id);
    }

    @RequestMapping(path = "/contas/{id}/depositoform"  , method = RequestMethod.POST)

    public String deposito (Long id, double valor ){
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setBalanco(conta.getBalanco() + valor );
        contaRepository.save(conta);
        return "index";
    }

    @RequestMapping(path = "/contas/{id}/leavantamentoform"  , method = RequestMethod.POST)

    public String levantamento (Long id , double valor) {
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (conta.getBalanco() < valor ){
            throw new RuntimeException("Valores insuficientes");

        }
        conta.setBalanco(conta.getBalanco() - valor );
        contaRepository.save(conta);
        return "index";
    }

    @ResponseBody
    @PostMapping("/contas/{id}/levantamento")

    public Conta levantamento(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        return contaServico.levantamento(id, valor);
    }



    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.PUT)
    public void atualizar (@RequestBody Conta conta){
        contaRepository.save(conta);
    }


    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas/{id}", method = RequestMethod.DELETE)
    public void apagar (@PathVariable Long id) {
        contaRepository.deleteById(id);

    }
}
