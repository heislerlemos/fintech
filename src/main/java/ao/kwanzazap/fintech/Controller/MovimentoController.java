package ao.kwanzazap.fintech.Controller;
import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Interface.MovimentoRepository;
import ao.kwanzazap.fintech.Model.Conta;
import ao.kwanzazap.fintech.Model.Movimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@Controller

public class MovimentoController {
    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ContaRepository contaRepository;


    @GetMapping("/movimento/{id}")

    public String vermovimentos(Model model  , @PathVariable int id){



        model.addAttribute("movimentos", movimentoRepository.findByContaId(id));
        return  "movimentos";
    }


}
