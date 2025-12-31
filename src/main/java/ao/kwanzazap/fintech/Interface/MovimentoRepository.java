package ao.kwanzazap.fintech.Interface;
import ao.kwanzazap.fintech.Model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MovimentoRepository  extends JpaRepository<Movimento, Long> {

}
