package ao.kwanzazap.fintech.Controller;
import java.util.List;
import java.util.Map;

import ao.kwanzazap.fintech.Service.ContaServico;
import ao.kwanzazap.fintech.Model.Conta;
import ao.kwanzazap.fintech.Interface.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/contas/{id}/deposito")
    public Conta deposito (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        return contaServico.deposito(id,valor);
    }

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
