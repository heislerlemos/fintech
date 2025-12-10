package ao.kwanzazap.fintech.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class LevantamentoController {

    @RequestMapping("/levantamento/{id}")

    public String  verlevantamento(){
        return  "levantamento";

    }

}
