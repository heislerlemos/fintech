package ao.kwanzazap.fintech.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta")



public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero_de_conta;
    private Double  balanco;
    private String nome_do_titular;
    private String iban;
    private Integer pin;
    @Column(length = 512)
    private String avatar;
    @OneToMany(mappedBy = "conta")
    private List<Movimento>  movimentos ;

}
