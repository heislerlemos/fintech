package ao.kwanzazap.fintech.Controller;
import ao.kwanzazap.fintech.Controller.ContaController;

import java.sql.Date;
import java.time.LocalDate;

import ao.kwanzazap.fintech.Interface.ContaRepository;
import ao.kwanzazap.fintech.Interface.MovimentoRepository;
import ao.kwanzazap.fintech.Model.Conta;
import ao.kwanzazap.fintech.Model.Movimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.time.LocalTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller

public class MovimentoController {
    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/movimento/{id}")

    public String vermovimentos () {
        return "movimentos";
    }


    //@RequestMapping(path = "/contas/{id}/depositoform"  , method = RequestMethod.POST)

    public String guardarmovimentosdeposito(Model model , @PathVariable long id){


        Movimento movimento = new Movimento();
        LocalDate Date3 = LocalDate.now();
        movimento.setData(Date.valueOf(Date3));
        movimentoRepository.save(movimento);
        return  "deposito";
    }


}
