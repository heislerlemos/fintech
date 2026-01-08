package ao.kwanzazap.fintech.Controller;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import ao.kwanzazap.fintech.Interface.MovimentoRepository;
import ao.kwanzazap.fintech.Model.Movimento;
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

    // Importanto as interface jpa de Conta e Movimento
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaServico contaServico;
    @Autowired
    private MovimentoRepository movimentoRepository;

    // Este metodo rende a pagina raiz da aplicação todas as contas criadas por ordem decrescente
    @GetMapping("/")
    public String contas(Model model){
        model.addAttribute("contas", contaRepository.findAll((Sort.by(Sort.Direction.DESC, "id"))));
        return "index";
    }

    // Este metodo é para ser testado com o HTTP client como postman e irá render a contas criadas em formato json
    @ResponseBody
    @RequestMapping("/contas")
    public List<Conta> contasj (){
        return contaRepository.findAll();
    }

    // Este metodo pega individualmente cada conta criada pelo id  rende em formato json com um HTTP client com o postman
    @ResponseBody
    @GetMapping("/contas/{id}")
    public Conta getAccount(@PathVariable Long id) {
        return contaServico.getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    // Este metodo cria uma contas em formato json a partir de um cliente HTTP client como o postman terá que inserir os parametros e o valor
    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.POST)
    public void guardar (@RequestBody Conta conta) {
        contaRepository.save(conta);
    }

    // Este metodo deposita dinheiro em uma determinada conta incrementando o balanço desta conta é utilizado com um client HTTP post terá que inserir os paramentros e o valor
    @ResponseBody
    @PostMapping("/contas/{id}/deposito")
    public Conta deposito (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        return contaServico.deposito(id,valor);
    }

    // Este metodo levanta dinheiro em uma determinada conta decrementando o balanço desta conta é utilizado com um client HTTP post terá que inserir os paramentros e o valor
    @ResponseBody
    @PostMapping("/contas/{id}/levantamento")
    public Conta levantamento(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double valor = request.get("valor");
        return contaServico.levantamento(id, valor);
    }


    public Optional<Conta> getConta (Long id ) {
        return contaRepository.findById(id);
    }

    // Este metodo deposita dinheiro na conta pela form de submissão da pagina deposito.html e inseri os detalhes do movimento associados a esta conta de seguida redireciona para a pagina raiz
    @RequestMapping(path = "/contas/{id}/depositoform"  , method = RequestMethod.POST)
    public String deposito (Long id, double valor ){
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setBalanco(conta.getBalanco() + valor );
        contaRepository.save(conta);
        Movimento movimento = new Movimento();
        movimento.setBalanco(conta.getBalanco());
        movimento.setMovimento(valor);
        movimento.setTipo("+");
        movimento.setData(new Date());
        movimento.setConta(conta);
        movimento.setNumero_de_conta(conta.getNumero_de_conta());
        movimentoRepository.save(movimento);
        return "redirect:/";
    }
    // Este metodo levanta dinheiro na conta pela form de submissão da pagina levantmanento.html e inseri os detalhes do movimento associados a esta conta de seguida redireciona para a pagina raiz
    @RequestMapping(path = "/contas/{id}/leavantamentoform"  , method = RequestMethod.POST)
    public String levantamento (Long id , double valor) {
        Conta conta = getConta(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (conta.getBalanco() < valor ){
            throw new RuntimeException("Valores insuficientes");
        }
        conta.setBalanco(conta.getBalanco() - valor );
        contaRepository.save(conta);
        Movimento movimento = new Movimento();
        movimento.setBalanco(conta.getBalanco());
        movimento.setMovimento(valor);
        movimento.setTipo("-");
        movimento.setData(new Date());
        movimento.setConta(conta);
        movimento.setNumero_de_conta(conta.getNumero_de_conta());
        movimentoRepository.save(movimento);
        return "redirect:/";
    }
    // Este metodo atualiza  as informações de uma determinada conta utilizando o HTTP client postman os parametros e valor são em formato json
    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas", method = RequestMethod.PUT)
    public void atualizar (@RequestBody Conta conta){
        contaRepository.save(conta);
    }


    // Este metodo apaga as informações de uma determinada conta utilizando o HTTP client postman
    @ResponseBody
    @Transactional
    @RequestMapping(path = "/contas/{id}", method = RequestMethod.DELETE)
    public void apagar (@PathVariable Long id) {
        contaRepository.deleteById(id);

    }
}
