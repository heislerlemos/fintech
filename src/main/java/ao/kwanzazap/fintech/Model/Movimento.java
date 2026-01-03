package ao.kwanzazap.fintech.Model;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor



public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero_de_conta;
    private Date data;
    private Double  balanco;
    private Double movimento;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;


}
