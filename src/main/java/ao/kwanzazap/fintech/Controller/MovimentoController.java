package ao.kwanzazap.fintech.Controller;
import ao.kwanzazap.fintech.Interface.MovimentoRepository;
import ao.kwanzazap.fintech.Model.Movimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;


@Controller

public class MovimentoController {
    @Autowired
    private MovimentoRepository movimentoRepository;


    @GetMapping("/movimento/{id}")

    public String vermovimentos(Model model , @PathVariable long id){

        try {
            Movimento movimento = movimentoRepository.findById(id).get();
            model.addAttribute("movimento", movimento);

            Movimento movimento1 = new Movimento();
            movimento1.setId(movimento.getId());
            movimento1.setNumero_de_conta(movimento.getNumero_de_conta());
            movimento1.setBalanco(movimento.getBalanco());
            movimento1.setData(movimento.getData());
            movimento1.setTipo(movimento.getTipo());
            movimento1.setConta(movimento.getConta());

            model.addAttribute("movimento1", movimento1);

        } catch (Exception e){
            System.out.println("Error");
        }

        return  "movimentos";
    }


}
