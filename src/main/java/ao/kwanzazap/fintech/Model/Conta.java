package ao.kwanzazap.fintech.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor



public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero_de_conta;
    private Double  balanco;
    private String nome_do_titular;
    private String iban;
    private Integer pin;
    @Column(length = 512)
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Movimentos movimentos;
    @Enumerated(EnumType.STRING)
    private Servicos servicos;


}
