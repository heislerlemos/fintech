package ao.kwanzazap.fintech;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor




public class Conta {
    @Id
    private Integer numero_de_conta;
    private Integer saldo;
    private String nome_do_titular;
    private String iban;
    private Integer pin;
    @Enumerated(EnumType.STRING)
    private Movimentos movimentos;
    @Enumerated(EnumType.STRING)
    private Servicos servicos;

}
