package ao.kwanzazap.fintech.Controller;
import java.time.Instant;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import ao.kwanzazap.fintech.Interface.MovimentoRepository;
import ao.kwanzazap.fintech.Model.Movimento;
import ao.kwanzazap.fintech.Service.ContaServico;
import ao.kwanzazap.fintech.Model.Conta;
import ao.kwanzazap.fintech.Interface.ContaRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller

public class ContaController
{
    @CreationTimestamp
    private Instant createdOn;

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaServico contaServico;
    @Autowired
    private MovimentoRepository movimentoRepository;

    // HTML TEMPLATE VIEW
    @GetMapping("/")
    public String contas(Model model){
        model.addAttribute("contas", contaRepository.findAll((Sort.by(Sort.Direction.DESC, "id"))));
        return "index";
    }

    // JSON VIEW
    @ResponseBody
    @RequestMapping("/contas")
    public List<Conta> contasj (){
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
    public Conta deposito (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        return contaServico.deposito(id,valor);
    }

    @ResponseBody
    @PostMapping("/contas/{id}/levantamento")

    public Conta levantamento(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        return contaServico.levantamento(id, valor);
    }


    public Optional<Conta> getConta (Long id ) {
        return contaRepository.findById(id);
    }

    @RequestMapping(path = "/contas/{id}/depositoform"  , method = RequestMethod.POST)

    public String deposito (Long id, double valor ){
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setBalanco(conta.getBalanco() + valor );
        contaRepository.save(conta);
        Movimento movimento = new Movimento();
        movimento.setBalanco(conta.getBalanco());
        movimento.setMovimento(valor);
        movimento.setTipo("Deposito ++");
        movimento.setData(new Date());
        movimento.setNumero_de_conta(conta.getNumero_de_conta());


        movimentoRepository.save(movimento);
        return "redirect:/";
    }

    @RequestMapping(path = "/contas/{id}/leavantamentoform"  , method = RequestMethod.POST)

    public String levantamento (Long id , double valor) {
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (conta.getBalanco() < valor ){
            throw new RuntimeException("Valores insuficientes");

        }
        conta.setBalanco(conta.getBalanco() - valor );
        contaRepository.save(conta);
        return "redirect:/";
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
