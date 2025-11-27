package ao.kwanzazap.fintech;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor



public class Conta {
    private Integer numero_de_conta;
    private Integer saldo;
    private String nome_do_titular;
    private String iban;
    private Integer pin;
    private Movimentos movimentos;
    private Servicos servicos;

}
