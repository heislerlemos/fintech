package ao.kwanzazap.fintech.Controller;

import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller

public class LevantamentoController {

    @Autowired
    private ContaRepository contaRepository;


    @GetMapping("/levantamento/{id}")

    public String  verlevantamento(Model model , @PathVariable long id){

        try {
            Conta conta = contaRepository.findById(id).get();
            model.addAttribute("conta", conta);

            Conta conta1 = new Conta();
            conta1.setId(conta.getId());
            conta1.setBalanco(conta.getBalanco());

            model.addAttribute("conta1", conta1);

        } catch (Exception e){
            System.out.println("Erro:"+ e.getMessage());
            return "redirect:index";
        }
        return  "levantamento";

    }


}
